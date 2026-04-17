package raffaele.U5_w2_d5.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import raffaele.U5_w2_d5.entities.Dipendente;
import raffaele.U5_w2_d5.exeptionn.BadRequestException;
import raffaele.U5_w2_d5.exeptionn.NotFoundException;
import raffaele.U5_w2_d5.payload.DipendenteDTO;
import raffaele.U5_w2_d5.repository.DipendenteRepository;
import java.io.IOException;
import java.util.Map;



@Service
public class DipendenteService {
    private final DipendenteRepository dipendenteRepository;
    private final Cloudinary cloudinaryUploader;

    public DipendenteService(DipendenteRepository dipendenteRepository ,Cloudinary cloudinaryUploader) {
        this.dipendenteRepository = dipendenteRepository;
        this.cloudinaryUploader=cloudinaryUploader;
    }
    public Dipendente salva(DipendenteDTO body) {
        if(this.dipendenteRepository.existsByEmail(body.email())) throw new BadRequestException("L'indirizzo email " + body.email() + " è già in uso!");

        Dipendente nuovodipendente = new Dipendente(body.username(), body.email(), body.cognome(), body.nome());

        return dipendenteRepository.save(nuovodipendente);
    }
    public Dipendente findById(long id) {
        return dipendenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Dipendente " + id + " non trovato"));
    }
    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendenteRepository.findAll(pageable);
    }
    public void findByIdAndDelete(long dipendenteId) {
        Dipendente trovato = this.findById(dipendenteId);
        this.dipendenteRepository.delete(trovato);
    }
    public Dipendente avatarUpload(MultipartFile file, long id) {
        // 1. Controlli (file non più grande di tot, tipologia di file deve essere solo .gif,...)
        // 2. find by id dell'utente
        // 3. Upload del file su Cloudinary
        Dipendente dipendente=this.findById(id);

        try {
            Map result = cloudinaryUploader.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("secure_url");
            System.out.println(url);
            dipendente.setImg(url);
            return dipendenteRepository.save(dipendente);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

package raffaele.U5_w2_d5.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import raffaele.U5_w2_d5.entities.Viaggio;
import raffaele.U5_w2_d5.exeptionn.BadRequestException;
import raffaele.U5_w2_d5.exeptionn.NotFoundException;
import raffaele.U5_w2_d5.payload.ViaggioDTO;
import raffaele.U5_w2_d5.repository.ViaggioRepository;

import java.util.List;

@Service
public class ViaggioService {
    private final ViaggioRepository viaggioRepository;

    public ViaggioService(ViaggioRepository viaggioRepository) {
        this.viaggioRepository = viaggioRepository;
    }

    public Viaggio salva(ViaggioDTO body){
        if (viaggioRepository.existsByData(body.data())) throw new BadRequestException("Ce gia un viaggio per la data: " + body.data());

        //so senza controlli
        Viaggio viaggio= new Viaggio(body.destinazione(),body.data(),body.stato());
        return viaggioRepository.save(viaggio);
    }
    public Viaggio findById(long id) {
        return viaggioRepository.findById(id).orElseThrow(() -> new NotFoundException("Viaggio " + id + " non trovato"));
    }
    public Page<Viaggio> findAll(int page, int size, String sortBy){
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.viaggioRepository.findAll(pageable);
    }
    public Viaggio  findByIdAndUpdate(long viaggioId,ViaggioDTO body ){
        Viaggio viaggio = this.findById(viaggioId);
        viaggio.setData(body.data());
        viaggio.setDestinazione(body.destinazione());
        viaggio.setStato(body.stato());

       return viaggio;
    }
    public void findByIdAndDelete(long viaggioId) {
        Viaggio viaggio = this.findById( viaggioId);
        this.viaggioRepository.delete(viaggio);
    }

    public Viaggio updateStato(Long id, String stato) {
        Viaggio viaggio = findById(id);
        viaggio.setStato(stato);
        return viaggioRepository.save(viaggio);
    }
}

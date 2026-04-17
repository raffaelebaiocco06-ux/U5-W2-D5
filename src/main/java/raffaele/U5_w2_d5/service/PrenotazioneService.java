package raffaele.U5_w2_d5.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import raffaele.U5_w2_d5.entities.Dipendente;
import raffaele.U5_w2_d5.entities.Prenotazione;
import raffaele.U5_w2_d5.entities.Viaggio;
import raffaele.U5_w2_d5.exeptionn.BadRequestException;
import raffaele.U5_w2_d5.exeptionn.NotFoundException;
import raffaele.U5_w2_d5.payload.PrenotazioneDTO;
import raffaele.U5_w2_d5.repository.PrenotazoneRepository;


import java.time.LocalDate;

@Service
public class PrenotazioneService {
    private final PrenotazoneRepository prenotazioneRepository;
    private final DipendenteService dipendenteService;
    private final ViaggioService viaggioService;

    public PrenotazioneService(PrenotazoneRepository prenotazioneRepository, DipendenteService dipendenteService, ViaggioService viaggioService) {
        this.prenotazioneRepository = prenotazioneRepository;
        this.dipendenteService = dipendenteService;
        this.viaggioService = viaggioService;
    }

    public Prenotazione salva(PrenotazioneDTO body) {
        Dipendente dipendente = dipendenteService.findById(body.dipendenteId());
        Viaggio viaggio = viaggioService.findById(body.viaggioId());
        if (prenotazioneRepository.existsByDipendenteIdAndViaggioData(dipendente.getId(), viaggio.getData())) {
            throw new BadRequestException("Il dipendente " + dipendente.getNome() + " è già impegnato in un viaggio per la data " + viaggio.getData());
        }
        Prenotazione nuovaprenotazione = new Prenotazione(dipendente,viaggio,body.dataRichiesta(),body.descrizione());
        return prenotazioneRepository.save(nuovaprenotazione);
    }

    public Page<Prenotazione> findAll(int page, int size, String sortBy) {
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioneRepository.findAll(pageable);
    }

    public Prenotazione findById(long id) {
        return prenotazioneRepository.findById(id).orElseThrow(() -> new NotFoundException("Prenotazione " + id + " non trovata"));
    }

    public void findByIdAndDelete(long id) {
        Prenotazione trovata = this.findById(id);
        this.prenotazioneRepository.delete(trovata);
    }
}


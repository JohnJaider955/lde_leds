package co.edu.umanizales.ldeled.service;

import co.edu.umanizales.ldeled.model.ListDE;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class ListDELedService {
    private ListDE leds;

    public ListDELedService() {
        leds = new ListDE();
    }
}

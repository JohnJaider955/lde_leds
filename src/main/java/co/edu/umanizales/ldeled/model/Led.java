package co.edu.umanizales.ldeled.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class Led {
    private int id;
    private boolean ledState;
    private LocalTime dateOn;
    private LocalTime dateOff;

    public Led(int id)
    {
        this.id = id;
        ledState = false;
    }
}

package automining.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString

public class ConfigGson {

    private String ip;
    private String port;
    private String pathToLog;
    private String rigName;
    private String charset;
    private String temperatureArg;
    private String totalArg;
    private String mhsArg;
    private String type;
    private String key;

}

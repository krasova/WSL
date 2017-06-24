package wundershoppinglist.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

/**
 * Created by osamo on 6/23/2017.
 */
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class Task {
    private long id;
    private String created_at;
    private String title;
    private long list_id;
    private String type;
    private String revision;
    private boolean starred;
    private boolean completed;
    private String created_by_id;
}

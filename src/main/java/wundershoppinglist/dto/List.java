package wundershoppinglist.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

/**
 * Created by osamo on 6/22/2017.
 */
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class List {
    private String id;
    private String created_at;
    private String title;
    private String list_type;
    private String type;
    private String revision;
    private String owner_type;
    private String owner_id;
    private String created_by_request_id;
}

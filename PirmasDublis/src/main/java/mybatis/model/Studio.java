package mybatis.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Studio {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STUDIO.ID
     *
     * @mbg.generated Fri Mar 25 21:15:25 EET 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STUDIO.CITY
     *
     * @mbg.generated Fri Mar 25 21:15:25 EET 2022
     */
    private String city;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.STUDIO.NAME
     *
     * @mbg.generated Fri Mar 25 21:15:25 EET 2022
     */
    private String name;

    private List<Game> games;
}
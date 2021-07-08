package cn.sqh.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "valve_info")
public class ValveInfo implements Cloneable {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private Float temperature;
    @Column(name = "liquidLevel")
    private Float liquidLevel;
    @Column(name = "remainPower")
    private Integer remainPower;
    private Integer dbm;
    @Column(name = "cardNum")
    private String cardNum;
    @Column(name = "uploadTime")
    private Date uploadTime;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

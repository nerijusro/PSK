package mif.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "OPT_LOCK_EXCEPTION_LOG")
@Getter @Setter
public class OptLockExRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "TIMESTAMP")
    private String timestamp;

    @Column(name = "EXCEPTION_MESSAGE")
    private String exceptionMessage;

    public OptLockExRecord(){
    }

    public OptLockExRecord(String exceptionMessage){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date timeNow = new Date(System.currentTimeMillis());
        timestamp = formatter.format(timeNow);

        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptLockExRecord record = (OptLockExRecord) o;
        return Objects.equals(id, record.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
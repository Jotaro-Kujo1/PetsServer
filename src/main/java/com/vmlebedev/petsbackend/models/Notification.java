package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "notifications", schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Notification implements Comparable<Notification>{
    @Id
    @JsonProperty("id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @JsonProperty("profimg")
    private String profimg;
    @JsonProperty("text")
    private String text;
    @JsonProperty("sender_login")
    @Column(name = "sender_login")
    private String senderLogin;
    @JsonProperty("receiver_login")
    @Column(name = "receiver_login")
    private String receiverLogin;
    @JsonProperty("date")
    private String date;
    @Column(name = "created_date")
    @CreatedDate
    @Timestamp
    private Date createdDate;
    @Column(name = "modified_date")
    @LastModifiedDate
    @Timestamp
    private Date modifiedDate;

    public Notification(String id, String profimg, String text, String senderLogin, String receiverLogin, String date) {
        this.id = id;
        this.profimg = profimg;
        this.text = text;
        this.senderLogin = senderLogin;
        this.receiverLogin = receiverLogin;
        this.date = date;
    }

    private static List<Integer> treatment(Notification notification){
        String [] dat = new String[2];
        dat = notification.getDate().split(" ");

        String time = dat[1];
        String data = dat[0];

        String [] newTime = new String[2];
        newTime = time.split(":");

        int hour = Integer.parseInt(newTime[0]);
        int minutes = Integer.parseInt(newTime[1]);

        String [] newData = new String[3];
        newData = data.split("-");

        int day = Integer.parseInt(newData[0]);
        int mounth = Integer.parseInt(newData[1]);
        int year = Integer.parseInt(newData[2]);



        List<Integer> list = new ArrayList<Integer>();
        list.add(hour);
        list.add(minutes);
        list.add(day);
        list.add(mounth);
        list.add(year);

        return list;
    }

    @Override
    public int compareTo(Notification o) {
        List<Integer> first = Notification.treatment(this);
        List<Integer> second = Notification.treatment(o);

        if(first.get(4) > second.get(4)){
            return -1;
        }else if(first.get(4) < second.get(4)){
            return 1;
        }else{
            if(first.get(3)>second.get(3)){
                return -1;
            }else if(first.get(3) < second.get(3)){
                return 1;
            }else{
                if(first.get(2)>second.get(2)){
                    return -1;
                }else if(first.get(2)<second.get(2)){
                    return 1;
                }else{
                    if(first.get(0)>second.get(0)){
                        return -1;
                    }else if(first.get(0)<second.get(0)){
                        return 1;
                    }else{
                        if(first.get(1)>second.get(1)){
                            return -1;
                        }else return 1;
                    }
                }
            }
        }
    }
}

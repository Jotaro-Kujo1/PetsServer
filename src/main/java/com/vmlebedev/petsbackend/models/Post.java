package com.vmlebedev.petsbackend.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.Before;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="posts", schema = "public")
public class Post implements Comparable<Post>{
    @Id
    @JsonProperty("id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @JsonProperty("img")
    private byte[] img;
    @JsonProperty("description")
    private String description;
    @JsonProperty("address")
    private String address;
    @JsonProperty("login")
    private String login;
    @JsonProperty("handler")
    private String handler;
    @JsonProperty("date")
    private String date;
    @JsonProperty("area")
    private String area;
    @JsonProperty("state")
    private boolean state;
    @JsonProperty("profimg")
    private String profimg;

    private static List<Integer> treatment(Post post){
        String [] dat = new String[2];
        dat = post.getDate().split(" ");

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
    public int compareTo(Post o) {
        List<Integer> first = Post.treatment(this);
        List<Integer> second = Post.treatment(o);

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

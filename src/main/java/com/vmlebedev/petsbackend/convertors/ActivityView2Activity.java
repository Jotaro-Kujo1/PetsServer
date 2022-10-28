package com.vmlebedev.petsbackend.convertors;

import com.vmlebedev.petsbackend.models.Activity;
import com.vmlebedev.petsbackend.views.ActivityView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ActivityView2Activity implements Converter<ActivityView, Activity> {
    @Override
    public Activity convert(ActivityView source) {
        return new Activity("",0,0,0,source.getLogin());
    }
}

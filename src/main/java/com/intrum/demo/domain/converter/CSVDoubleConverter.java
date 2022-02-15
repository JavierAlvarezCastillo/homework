package com.intrum.demo.domain.converter;

import com.opencsv.bean.AbstractBeanField;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;

public class CSVDoubleConverter extends AbstractBeanField<Double, String> {

    @Override
    protected Object convert(String value)  {
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number number = null;
        try {
            number = format.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(number).doubleValue();
    }
}

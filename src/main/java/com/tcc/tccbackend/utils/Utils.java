package com.tcc.tccbackend.utils;

import com.tcc.tccbackend.dtos.AgendamentoDTO;
import com.tcc.tccbackend.models.Agendamento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class Utils {

    public static String extrairExtensao(String nomeArquivo) {
        int i = nomeArquivo.lastIndexOf(".");
        return nomeArquivo.substring(i + 1);
    }

    public static String formatter(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }

    public static String toDate(Date date) {
        return Utils.formatter(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().toLocalDate());
    }

    public static String formatterDataHora(String agendaDate) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
        Date dateParse = inputFormat.parse(agendaDate);

        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        return outputFormat.format(dateParse);
    }
    public static boolean verificaHorario(List<Agendamento> agendamentos, Agendamento agendamento) {
        for (Agendamento a : agendamentos) {
            if (a.getData().equals(agendamento.getData())) {
                return true;
            }
        }
        return false;
    }
}

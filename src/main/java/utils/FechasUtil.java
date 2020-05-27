/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import EJB.CitaFacadeLocal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import modelo.Cita;
import modelo.Doctor;

/**
 *
 * @author "Adrián", "Daniel"
 */
public class FechasUtil {
    public Date buscaFecha(Doctor doc, String horario, CitaFacadeLocal citaEJB){
        Date fecha = null;
        List<Cita> citasDoctor = citaEJB.buscarCitas(doc);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date hoy = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(hoy);
        c.set(Calendar.YEAR, 2020);
        c.add(Calendar.HOUR, 24);
        int unroundedMinutes = c.get(Calendar.MINUTE); 
        int mod = unroundedMinutes % 30; 
        c.add(Calendar.MINUTE, mod < 8 ? -mod : (30-mod));
        
        if(citasDoctor == null){
            switch (horario) {
                case "Ambas":          
                    fecha = c.getTime();
                break;
                case "Mañanas":
                    if(c.get(Calendar.HOUR_OF_DAY) >= 9 && c.get(Calendar.HOUR_OF_DAY) <= 13){
                        fecha = c.getTime();
                    }else{
                        c.add(Calendar.DAY_OF_MONTH, 1);
                        c.setTime(new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 9, 0));
                        fecha = c.getTime();
                    }     
                break;
                default:
                    if(c.get(Calendar.HOUR_OF_DAY) >= 16 && c.get(Calendar.HOUR_OF_DAY) <= 20){
                        fecha = c.getTime();
                    }else{
                        c.add(Calendar.DAY_OF_MONTH, 1);
                        c.setTime(new Date(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 16, 0));
                        fecha = c.getTime();
                    } 
                break;
            }
        }else{
            switch (horario) {
                case "Ambas":
                    do{
                        boolean libre = true;
                        for(int i = 0; i < citasDoctor.size(); i++){
                            if(c.getTime().getDate() == citasDoctor.get(i).getFecha().getDate() && c.getTime().getHours() == citasDoctor.get(i).getFecha().getHours() && c.getTime().getMinutes() == citasDoctor.get(i).getFecha().getMinutes() && c.getTime().getMonth() == citasDoctor.get(i).getFecha().getMonth()){
                                libre = false;
                            }
                        }
                        if(libre == true && (c.get(Calendar.HOUR_OF_DAY) >= 9 && c.get(Calendar.HOUR_OF_DAY) <= 20)) {
                            fecha = c.getTime();
                        }else{
                            c.add(Calendar.MINUTE, 30);
                        }
                    }while(fecha == null);
                break;
                case "Mañanas":
                    do{
                        boolean libre = true;
                        for(int i = 0; i < citasDoctor.size(); i++){
                            if(c.getTime().getDate() == citasDoctor.get(i).getFecha().getDate() && c.getTime().getHours() == citasDoctor.get(i).getFecha().getHours() && c.getTime().getMinutes() == citasDoctor.get(i).getFecha().getMinutes() && c.getTime().getMonth() == citasDoctor.get(i).getFecha().getMonth()){
                                libre = false;
                            }
                        }
                        if(libre == true && (c.get(Calendar.HOUR_OF_DAY) >= 9 && c.get(Calendar.HOUR_OF_DAY) <= 13)) {
                            fecha = c.getTime();
                        }else{
                            c.add(Calendar.MINUTE, 30);
                        }
                    }while(fecha == null);
                break;
                default:
                    do{
                        boolean libre = true;
                        for(int i = 0; i < citasDoctor.size(); i++){
                            if(c.getTime().getDate() == citasDoctor.get(i).getFecha().getDate() && c.getTime().getHours() == citasDoctor.get(i).getFecha().getHours() && c.getTime().getMinutes() == citasDoctor.get(i).getFecha().getMinutes() && c.getTime().getMonth() == citasDoctor.get(i).getFecha().getMonth()){
                                libre = false;
                            }
                        }
                        if(libre == true && (c.get(Calendar.HOUR_OF_DAY) >= 16 && c.get(Calendar.HOUR_OF_DAY) <= 20)) {
                            fecha = c.getTime();
                        }else{
                            c.add(Calendar.MINUTE, 30);
                        }
                    }while(fecha == null);
                break;
            }
        }
        
        return fecha;
    }
}

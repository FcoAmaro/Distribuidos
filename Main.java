import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.*;  

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.net.InetAddress;
import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Thread;
import java.util.Set;
import java.util.logging.*;

public class Main{
   
    static String[] ips = { "10.6.40.145", "10.6.40.146", "10.6.40.147", "10.6.40.148"};
    static String json1 = "doctor.json";
    static String json2 = "requerimientos.json";
    static String json3 = "pacientes.json";

    public static void main(String[] args) throws Exception{
        //listas con los trabajadores locales
        Scanner sc = new Scanner(System.in); 
        List<Doctor> doctoreslocales = new ArrayList<Doctor>();
        List<Enfermero> enfermeroslocales = new ArrayList<Enfermero>();
        List<Paramedico> paramedicoslocales = new ArrayList<Paramedico>();
        List<Requerimientos> requerimientoslocales = new ArrayList<Requerimientos>();
        List<Pacientes> pacientesglobales = new ArrayList<Pacientes>();

        String direccion=InetAddress.getLocalHost().getHostAddress().toString();
        System.out.println(direccion);


        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(json1));
        JSONObject jsonObject =  (JSONObject) obj;
        JSONArray doctor= (JSONArray) jsonObject.get("Doctor");
        JSONArray enfermero= (JSONArray) jsonObject.get("Enfermero");
        JSONArray paramedico= (JSONArray) jsonObject.get("Paramedico");


        JSONParser parser2 = new JSONParser();
        Object obj2 = parser2.parse(new FileReader(json2));
        JSONObject jsonObject2 =  (JSONObject) obj2;
        JSONArray requerimientos= (JSONArray) jsonObject2.get("requerimientos");

        int procesos = doctor.size() + enfermero.size() + paramedico.size();
        int nProc = -1;
        MyThread[] t = new MyThread[procesos];

        for(int i=0; i<doctor.size(); i++){
            JSONObject rec =(JSONObject) doctor.get(i);
            String nombre=rec.get("nombre").toString();
            String apellido=rec.get("apellido").toString();
            int id=(int) (long) rec.get("id");
            int estudios=(int) (long) rec.get("estudios");
            int experiencia=(int) (long) rec.get("experiencia");
            Doctor doctorsito = new Doctor(nombre,apellido,id,estudios,experiencia);
            doctoreslocales.add(doctorsito);
            nProc++;
            t[nProc] = new MyThread(new Process(nProc,"Doctor "+(i+1),0,estudios+experiencia), procesos);
            System.out.println("Doctor " + doctoreslocales.get(i).getNombre());
        }

        for(int i=0; i<enfermero.size(); i++){
            JSONObject rec =(JSONObject) enfermero.get(i);
            String nombre=rec.get("nombre").toString();
            String apellido=rec.get("apellido").toString();
            int id=(int) (long) rec.get("id");
            int estudios=(int) (long) rec.get("estudios");
            int experiencia=(int) (long) rec.get("experiencia");
            Enfermero enfermesito = new Enfermero(nombre,apellido,id,estudios,experiencia);
            enfermeroslocales.add(enfermesito);
            nProc++;
            t[nProc] = new MyThread(new Process(nProc,"Enfermero "+(i+1),1,estudios+experiencia), procesos);
            System.out.println("Enfermero " + enfermeroslocales.get(i).getNombre());
        }


        for(int i=0; i<paramedico.size(); i++){
            JSONObject rec =(JSONObject) paramedico.get(i);
            String nombre=rec.get("nombre").toString();
            String apellido=rec.get("apellido").toString();
            int id=(int) (long) rec.get("id");
            int estudios=(int) (long) rec.get("estudios");
            int experiencia=(int) (long) rec.get("experiencia");
            Paramedico paramedisito = new Paramedico(nombre,apellido,id,estudios,experiencia);
            paramedicoslocales.add(paramedisito);
            nProc++;
            t[nProc] = new MyThread(new Process(nProc,"Paramédico "+(i+1),2,estudios+experiencia), procesos);
            System.out.println("Paramédico " + paramedicoslocales.get(i).getNombre());
        }

        for(int i=0; i<requerimientos.size(); i++){
            List<Pair> pacientesRequerimientos = new ArrayList<Pair>();

            JSONObject rec =(JSONObject) requerimientos.get(i);
            int id=(int) (long) rec.get("id");
            String cargoP=rec.get("cargo").toString();
            JSONArray pacientesP=(JSONArray) rec.get("pacientes");

            for(int j=0; j<pacientesP.size(); j++){
                JSONObject rec3 =(JSONObject) pacientesP.get(j);
                Set keys = rec3.keySet();
                Object[] llave=keys.toArray();
                int llaveId= Integer.valueOf((String) llave[0]);
                String valor=String.valueOf(llaveId);
                String procedimiento=rec3.get(valor).toString();
                Pair tupla = new Pair(llaveId,procedimiento);
                pacientesRequerimientos.add(tupla);

            }
            Requerimientos requerimiensitos = new Requerimientos(cargoP,id,pacientesRequerimientos);
            requerimientoslocales.add(requerimiensitos);

        }


        JSONParser parser3 = new JSONParser();
        Object obj3 = parser3.parse(new FileReader(json3));
        JSONArray array3 =  (JSONArray) obj3;
        //System.out.println(array3);

        for(int i=0; i<array3.size(); i++){
            List<String> pacienteEnfermedades = new ArrayList<String>();
            List<String> pacientesTrataA = new ArrayList<String>();
            List<String> pacientesTrataC = new ArrayList<String>();
            List<String> pacientesExaR = new ArrayList<String>();
            List<String> pacientesExaN = new ArrayList<String>();
            List<String> pacientesMeR = new ArrayList<String>();
            List<String> pacientesMeS = new ArrayList<String>();
            

            JSONObject elPaciente= (JSONObject) array3.get(i);
            int id=(int) (long) elPaciente.get("paciente_id");
            JSONArray datitos=(JSONArray) elPaciente.get("datos personales");
            JSONObject datos= (JSONObject) datitos.get(0);
            String nombre=datos.get("nombre").toString();
            String rut=datos.get("rut").toString();
            String edad=datos.get("edad").toString();
            JSONArray enfermePaciente=(JSONArray) elPaciente.get("enfermedades");
            for(int j=0; j<enfermePaciente.size(); j++){
                String enf=enfermePaciente.get(j).toString();
                pacienteEnfermedades.add(enf);
            }

            JSONArray tratiPaciente=(JSONArray) elPaciente.get("tratamientos/procedimientos");
            JSONObject trataPaciente= (JSONObject) tratiPaciente.get(0);
            JSONArray asignadosPaciente=(JSONArray) trataPaciente.get("asignados");
            for(int j=0; j<asignadosPaciente.size(); j++){
                String asg=asignadosPaciente.get(j).toString();
                pacientesTrataA.add(asg);
            }
            JSONObject trataPaciente2= (JSONObject) tratiPaciente.get(1);
            JSONArray completadosPaciente=(JSONArray) trataPaciente2.get("completados");
            for(int j=0; j<completadosPaciente.size(); j++){
                String com=completadosPaciente.get(j).toString();
                pacientesTrataC.add(com);
            }

            JSONArray exiPaciente=(JSONArray) elPaciente.get("examenes");
            JSONObject exaPaciente= (JSONObject) exiPaciente.get(0);
            JSONArray realizadosPaciente=(JSONArray) exaPaciente.get("realizados");
            for(int j=0; j<realizadosPaciente.size(); j++){
                String rea=realizadosPaciente.get(j).toString();
                pacientesExaR.add(rea);
            }

            JSONObject exaPaciente2= (JSONObject) exiPaciente.get(1);
            JSONArray norealizadosPaciente=(JSONArray) exaPaciente2.get("no realizados");
            for(int j=0; j<norealizadosPaciente.size(); j++){
                String norea=norealizadosPaciente.get(j).toString();
                pacientesExaN.add(norea);
            }

            JSONArray mediPaciente=(JSONArray) elPaciente.get("medicamentos");
            JSONObject medaPaciente= (JSONObject) mediPaciente.get(0);
            JSONArray recetadosPaciente=(JSONArray) medaPaciente.get("recetados");
            for(int j=0; j<recetadosPaciente.size(); j++){
                String rece=recetadosPaciente.get(j).toString();
                pacientesMeR.add(rece);
            }

            JSONObject medaPaciente2= (JSONObject) mediPaciente.get(1);
            JSONArray suministradosPaciente=(JSONArray) medaPaciente2.get("suministrados");
            for(int j=0; j<suministradosPaciente.size(); j++){
                String sumi=suministradosPaciente.get(j).toString();
                pacientesMeS.add(sumi);
            }

            Pacientes paciensitos = new Pacientes(id,nombre,rut,edad,pacienteEnfermedades,pacientesTrataA,pacientesTrataC,pacientesExaR,pacientesExaN,pacientesMeR,pacientesMeS);
            pacientesglobales.add(paciensitos);
            
        }

        MostrarRequerimientos(requerimientoslocales);

        try {
            MyServerSocket server = new MyServerSocket(direccion,20000);
            server.start();
            System.out.println("Entro a server");
            Thread.sleep(2000);
            MyClientSocket cliente = new MyClientSocket(direccion,20000,doctoreslocales,enfermeroslocales,paramedicoslocales,requerimientoslocales,pacientesglobales);
            cliente.start();
            System.out.println("Entro a cliente");
        }
        catch(Exception e){
             System.out.println(e.getMessage());
        }
    }


    public static void MostrarRequerimientos(List<Requerimientos> requerimientoslocales){
        Logger logger = Logger.getLogger("MyLog");  
        FileHandler fh; 
        try {
            // This block configure the logger with handler and formatter  
            fh = new FileHandler("LogFile.log");  
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter);  
            for(int j=0; j<requerimientoslocales.size(); j++){
                    //System.out.println("Id: "+requerimientoslocales.get(j).getId());
                    //System.out.println("Cargo: "+requerimientoslocales.get(j).getCargo());
                    //System.out.println("Mostrando requerimientos pacientes: ");
                    for(int i=0; i<requerimientoslocales.get(j).getListado().size(); i++){
                        logger.info("Accesso del "+requerimientoslocales.get(j).getCargo()+
                        " "+requerimientoslocales.get(j).getId()+" a ficha de paciente "+requerimientoslocales.get(j).getListado().get(i).getId()+
                        "\nProcedimiento: "+requerimientoslocales.get(j).getListado().get(i).getProcedimiento());

                    }
                    
            }
            System.out.println("Logfile generado");
        } catch (SecurityException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }   



    
}   




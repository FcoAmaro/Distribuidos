import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.net.InetAddress;
import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;
public class MyClientSocket extends Thread {
    private Socket socket;
    private Scanner scanner;
    private List<Doctor> localesdoctores;
    private List<Enfermero> localesenfermeros;
    private List<Paramedico> localesparamedicos;
    private List<Requerimientos> localesrequerimientos;
    private List<Pacientes> globalespacientes;



    public MyClientSocket(String serverAd, int serverPort, List<Doctor> doctores,List<Enfermero> enfermeros, List<Paramedico> paramedicos, List<Requerimientos> requerimientos,List<Pacientes> pacientes) throws Exception {
        this.socket = new Socket(InetAddress.getByName(serverAd), serverPort);
        this.scanner = new Scanner(System.in);
        this.localesdoctores=doctores;
        this.localesenfermeros=enfermeros;
        this.localesparamedicos=paramedicos;
        this.localesrequerimientos=requerimientos;
        this.globalespacientes=pacientes;

    }

    private void iniciar2() throws IOException {
        int coordinadorSugerido = 0;
        int experienciaCandidato = 0;
        for(int i=0; i<localesdoctores.size(); i++){
            int id=localesdoctores.get(i).getId();
            int estudios=localesdoctores.get(i).getEstudios();
            int experiencia=localesdoctores.get(i).getExperiencia();
            if (estudios+experiencia > experienciaCandidato){
                coordinadorSugerido = id;
                experienciaCandidato = estudios+experiencia;
            }
        }
        String msgServer = coordinadorSugerido+"/"+experienciaCandidato; 
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        out.println(msgServer);
        out.flush();
    }

    private void iniciar() throws IOException {
        String input;
        Scanner sc = new Scanner(System.in); 
        boolean salir=true;
        String cargo;
        int idElegido;
        int idPaciente;
        Pacientes pacienteElegido;
        while(salir){
                System.out.println("Ingresa numero: Doctor (0) , Paramedico (1), Enfermero(2), Salir(3)"); 
                int eleccion = sc.nextInt();
                if (eleccion == 0){
                    cargo="doctor";
                    boolean salir2=true;
                    while(salir2){
                        verDoctores(localesdoctores);
                        int eleccion2 = sc.nextInt();
                        if(eleccion2 == -1){
                            salir2=false;
                            break;
                        }
                        idElegido=localesdoctores.get(eleccion2).getId();
                        boolean salir3=true;
                        while(salir3){
                            System.out.println("Ingresa accion: Ver pacientes (0) , Editar ficha (1), Salir(2)");
                            int eleccion3 = sc.nextInt();
                            if (eleccion3==0){ 
                                verPacientes(globalespacientes);
                                int eleccion4 = sc.nextInt();
                                idPaciente=eleccion4;
                                pacienteElegido=globalespacientes.get(eleccion4);
                                mostrarDepaciente(pacienteElegido);

                            }

                            if (eleccion3==1){ 
                                verPacientes(globalespacientes);
                            }

                            if (eleccion3==2){ 
                                salir3=false;
                            }


                        }

                    }


                }


                if (eleccion == 1){
                    cargo="paramedico";
                    boolean salir2=true;
                    while(salir2){
                        verParamedicos(localesparamedicos);
                        int eleccion2 = sc.nextInt();
                        if(eleccion2 == -1){
                            salir2=false;
                            break;
                        }
                        idElegido=localesparamedicos.get(eleccion2).getId();
                        boolean salir3=true;
                        while(salir3){
                            System.out.println("Ingresa accion: Ver pacientes (0) , Editar ficha (1), Salir(2)");
                            int eleccion3 = sc.nextInt();
                            if (eleccion3==0){ 
                                verPacientes(globalespacientes);
                                int eleccion4 = sc.nextInt();
                                idPaciente=eleccion4;
                                pacienteElegido=globalespacientes.get(eleccion4);
                                mostrarDepaciente(pacienteElegido);

                            }

                            if (eleccion3==1){ 
                                verPacientes(globalespacientes);
                            }

                            if (eleccion3==2){ 
                                salir3=false;
                            }


                        }

                    }


                }

                if (eleccion == 2){
                    cargo="enfermero";
                    boolean salir2=true;
                    while(salir2){
                        verEnfermeros(localesenfermeros);
                        int eleccion2 = sc.nextInt();
                        if(eleccion2 == -1){
                            salir2=false;
                            break;
                        }
                        idElegido=localesenfermeros.get(eleccion2).getId();
                        boolean salir3=true;
                        while(salir3){
                            System.out.println("Ingresa accion: Ver pacientes (0) , Editar ficha (1), Salir(2)");
                            int eleccion3 = sc.nextInt();
                            if (eleccion3==0){ 
                                verPacientes(globalespacientes);
                                int eleccion4 = sc.nextInt();
                                idPaciente=eleccion4;
                                pacienteElegido=globalespacientes.get(eleccion4);
                                mostrarDepaciente(pacienteElegido);

                            }

                            if (eleccion3==1){ 
                                verPacientes(globalespacientes);
                            }

                            if (eleccion3==2){ 
                                salir3=false;
                            }


                        }

                    }


                }



                if(eleccion == 3){
                    salir=false;
                }
                
        }
        /*while (true) {
            input = scanner.nextLine();
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(input);
            out.flush();
        }
        */
    }
    
    public void run() {

        try {
            System.out.println("\r\nConnected to Server: " + socket.getInetAddress()+socket.getPort());
            iniciar();  
            }          


        catch (Exception e) {
                System.out.println("Entro a Exception cliente");
            }


    }


    private void verPacientes(List<Pacientes> pacientes) {
        System.out.println("Elija el paciente a ver:" );
        for(int i=0; i<pacientes.size(); i++){
                        String nombreactual=pacientes.get(i).getNombre();
                        int idactual=localesenfermeros.get(i).getId();
                        System.out.println("Numero eleccion "+i+", Id "+idactual+" Nombre "+nombreactual);
                    }


    }


    private void verDoctores(List<Doctor> doctores) {
        System.out.println("Elige el doctor (-1 salir):" );
        for(int i=0; i<doctores.size(); i++){
                        String nombreactual=doctores.get(i).getNombre();
                        int idactual=doctores.get(i).getId();
                        System.out.println("Numero eleccion "+i+", Id "+idactual+" Nombre "+nombreactual);
        }


    }

    private void verParamedicos(List<Paramedico> paramedico) {
        System.out.println("Elige el paramedico (-1 salir):" );
        for(int i=0; i<paramedico.size(); i++){
                        String nombreactual=paramedico.get(i).getNombre();
                        int idactual=paramedico.get(i).getId();
                        System.out.println("Numero eleccion "+i+", Id "+idactual+" Nombre "+nombreactual);
        }


    }

    private void verEnfermeros(List<Enfermero> enfermero) {
        System.out.println("Elige el enfermero (-1 salir):" );
        for(int i=0; i<enfermero.size(); i++){
                        String nombreactual=enfermero.get(i).getNombre();
                        int idactual=enfermero.get(i).getId();
                        System.out.println("Numero eleccion "+i+", Id "+idactual+" Nombre "+nombreactual);
        }


    }

    private void mostrarDepaciente(Pacientes paciente) {
        boolean salida=true;
        Scanner sc = new Scanner(System.in); 

        while(salida){
            System.out.println("Ingrese accion: ver datos(0), enfermedades(1), tratamientos(2),examenes(3),medicamentos(4),salir(5)" );
            int accion = sc.nextInt();

            if (accion==0){
                System.out.println("Nombre "+paciente.getNombre()+", rut "+paciente.getRut()+" edad "+paciente.getEdad());

            }

            if (accion==1){
                for(int i=0; i<paciente.getEnfermedades().size(); i++){
                        System.out.println(paciente.getEnfermedades().get(i));
                }
            }

            if (accion==2){
                System.out.println("Asignados:");
                for(int i=0; i<paciente.getTratamientosA().size(); i++){
                        System.out.println(paciente.getTratamientosA().get(i));
                }
                System.out.println("Completados:");
                for(int i=0; i<paciente.getTratamientosC().size(); i++){
                        System.out.println(paciente.getTratamientosC().get(i));
                }
            }

            if (accion==3){
                System.out.println("Realizados:");
                for(int i=0; i<paciente.examenesR().size(); i++){
                        System.out.println(paciente.examenesR().get(i));
                }
                System.out.println("No realizados:");
                for(int i=0; i<paciente.examenesN().size(); i++){
                        System.out.println(paciente.examenesN().get(i));
                }
            }

            if (accion==4){
                System.out.println("Recetados:");
                for(int i=0; i<paciente.medicamentosR().size(); i++){
                        System.out.println(paciente.medicamentosR().get(i));
                }
                System.out.println("Suministrados:");
                for(int i=0; i<paciente.medicamentosS().size(); i++){
                        System.out.println(paciente.medicamentosS().get(i));
                }
            }

            if (accion ==5){
                salida=false;
            }


            
        }


    }
}

import java.util.List; 
import java.util.Arrays;
import java.util.ArrayList;

public class Pacientes {
    private int id;
    private String nombre;
    private String rut;
    private String edad;
    private List<String> enfermedades;
    private List<String> tratamientosAsignados;
    private List<String> tratamientosCompletados;
    private List<String> examenesRealizados;
    private List<String> examenesNorealizados;
    private List<String> medicamentosRecetados;
    private List<String> medicamentosSuministrados;



    public Pacientes(int id,String nombre,String rut,String edad,List<String> enfermedades,List<String> tratamientosAsignados,List<String> tratamientosCompletados,List<String> examenesRealizados,List<String> examenesNorealizados,List<String> medicamentosRecetados,List<String> medicamentosSuministrados) {
        this.nombre = nombre;
        this.id=id;
        this.rut=rut;
        this.edad=edad;
        this.enfermedades=enfermedades;
        this.tratamientosAsignados=tratamientosAsignados;
        this.tratamientosCompletados=tratamientosCompletados;
        this.examenesRealizados=examenesRealizados;
        this.examenesNorealizados=examenesNorealizados;
        this.medicamentosRecetados=medicamentosRecetados;
        this.medicamentosSuministrados=medicamentosSuministrados;
        

    }

    public String getNombre(){
    	return this.nombre;

    }


     public int getId(){
    	return this.id;

    }

    public String getRut(){
        return this.rut;

    }


    public String getEdad(){
        return this.edad;

    }

    public List<String> getEnfermedades(){
        return this.enfermedades;

    }

    public List<String> getTratamientosA(){
        return this.tratamientosAsignados;

    }
    public List<String> getTratamientosC(){
        return this.tratamientosCompletados;

    }
    public List<String> examenesR(){
        return this.examenesRealizados;

    }
    public List<String> examenesN(){
        return this.examenesNorealizados;

    }
    public List<String> medicamentosR(){
        return this.medicamentosRecetados;

    }

    public List<String> medicamentosS(){
        return this.medicamentosSuministrados;

    }
}

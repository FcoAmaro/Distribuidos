public class Enfermero {
    private int id;
    private String Nombre;
    private String Apellido;
    private int Estudios;
    private int Experiencia;

    public Enfermero(String nombre,String apellido, int id, int estudios,int experiencia) {
        this.Nombre = nombre;
        this.id=id;
        this.Apellido=apellido;
        this.Estudios=estudios;
        this.Experiencia=experiencia;
    }
    public String getNombre(){
    	return this.Nombre;
    }

    public String getApellido(){
    	return this.Apellido;
    }

    public int getId(){
    	return this.id;
    }

    public int getEstudios(){
    	return this.Estudios;
    }
    public int getExperiencia(){
    	return this.Experiencia;
    }
}

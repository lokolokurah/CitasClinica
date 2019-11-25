package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {
	private static final String ER_DNI = ("([0-9]{8})([A-Za-z])");
	private static final String ER_TELEFONO = ("[69][0-9]{8}");
	private String nombre;
	private String dni;
	private String telefono;

	public Paciente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	public Paciente(Paciente copiaPaciente)
	{
		if (copiaPaciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		} else {
			setNombre(copiaPaciente.getNombre());
			setDni(copiaPaciente.getDni());
			setTelefono(copiaPaciente.getTelefono());
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.equals("")) { 
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		} else
		{
			this.nombre = formateaNombre(nombre);
		}  

	}

	private String formateaNombre(String nombre) {
		nombre=nombre.replaceAll("\\s{2,}", " ").trim();
		String[] palabras = nombre.split(" ");
		String nuevoNombre = "";
		for (int i=0; i<=palabras.length-1; i++) {
			palabras[i] = palabras[i].substring(0,1).toUpperCase() + palabras[i].substring(1).toLowerCase();
			nuevoNombre = nuevoNombre + palabras[i] + " ";
		}
		nombre = nuevoNombre.trim();
		return nombre;
	}

	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null || dni.equals("")) { 
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		} else {
			Pattern patron;
			Matcher comparador;

			patron = Pattern.compile(ER_DNI);
			comparador = patron.matcher(dni);

			if (comparador.matches()) {
				if (comprobarLetraDni(dni)==true) {
					this.dni = dni;
				} else {
					throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
				}
			} else {
				throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
			}
		}
	}

	private boolean comprobarLetraDni(String dni) {
		boolean verificador = false;

		Pattern patron;
		Matcher comparador;

		patron = Pattern.compile(ER_DNI);
		comparador = patron.matcher(dni);

		if (comparador.matches()) {
			int numeroDni = Integer.parseInt(comparador.group(1));
			String letraDni = comparador.group(2);
			String[] letraValida = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
					"V", "H", "L", "C", "K", "E" };
			if (comparador.group(2).equals(letraValida[numeroDni%23])) {
				verificador = true;
			} 
		}
		return verificador;
	}	

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null || telefono.equals("")) { 
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		} else if (telefono.matches(ER_TELEFONO))
		{
			this.telefono = telefono; 
		}  
		else 
		{
			throw new IllegalArgumentException("ERROR: El teléfono no tiene un formato válido.");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String toString = String.format("nombre=%s (%s), DNI=%s, teléfono=%s", getNombre(), getIniciales(), 
				getDni(), getTelefono());
		return toString;  
	}

	public String getIniciales()
	{
		String []palabra=nombre.split(" ");
		String iniciales="";
		for (int i=0;i<palabra.length;i++) {
			iniciales+=palabra[i].charAt(0);
		}
		return iniciales.toUpperCase();
	}
}

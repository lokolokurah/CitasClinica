package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Cita {

	public static final String FORMATO_FECHA_HORA = ("yyyy, mm, dd, hh, mm");
	private LocalDateTime fechaHora;
	public Paciente paciente;


	public Cita(Paciente paciente, LocalDateTime fechaHora) {
		super();
		setFechaHora(fechaHora);
		setPaciente(paciente);
	}

	public Cita(Cita copiaCita)
	{
		if (copiaCita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		} else {
			setFechaHora(copiaCita.getFechaHora());
			setPaciente(copiaCita.getPaciente());
		}
	}

	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDateTime fechaHora) {
		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}
		this.fechaHora = fechaHora;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	private void setPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		}
		this.paciente = new Paciente(paciente);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaHora == null) ? 0 : fechaHora.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
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
		Cita other = (Cita) obj;
		if (fechaHora == null) {
			if (other.fechaHora != null)
				return false;
		} else if (!fechaHora.equals(other.fechaHora))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return paciente.toString() + ", fechaHora=" + fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))	+ "";
	}
}

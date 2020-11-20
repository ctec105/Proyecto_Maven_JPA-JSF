package pe.edu.proyecto.primefaces.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import pe.edu.proyecto.util.Mensajes;

@Named
@RequestScoped
public class IdleMonitorView {

	public void onIdle() {
		Mensajes.warn("No activity.", "What are you doing over there?");
	}

	public void onActive() {
		Mensajes.warn("Welcome Back", "Well, that's a long coffee break!");
	}
}
package com.example.PruebaTia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

@SpringBootApplication
public class PruebaTiaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(PruebaTiaApplication.class)
				.headless(false)
				.run(args);

		openBrowser("http://localhost:8080/reporte"); // Abre el navegador automáticamente
	}

	private static void openBrowser(String url) {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				if (System.getProperty("os.name").toLowerCase().contains("win")) {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url); // Para Windows
				} else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
					Runtime.getRuntime().exec("open " + url); // Para macOS
				} else {
					Runtime.getRuntime().exec("xdg-open " + url); // Para Linux (o otros Unix-like)
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

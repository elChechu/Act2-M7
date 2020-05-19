/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package informes;

import conn.Connexio;
import entidades.Pelicula;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author rvallez
 */
public class FXMLDocumentController implements Initializable {

    private LinkedList<Pelicula> peliculas = new LinkedList<>();
    private final String query = "SELECT f.film_id, f.title, f.release_year, f.rating, l.name FROM film f, language l WHERE f.language_id = l.language_id limit 50;";
    private JasperPrint reportToPrint;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Connexio conexio = new Connexio();
            Connection con = conexio.conectar();
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery(query);

            while (result.next()) {
                peliculas.add(new Pelicula(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }

        try {
          
            JasperCompileManager.compileReport("src/informes/report.jrxml");
        } catch (JRException ex) {
            System.out.println(ex);
        }

        try {
            reportToPrint = JasperFillManager.fillReport("src/informes/report.jasper", null, new JRBeanCollectionDataSource(peliculas));
        } catch (JRException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    public void toPDF() throws JRException {
        Exporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(reportToPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("report.pdf"));
        exporter.exportReport();
    }

    @FXML
    public void toDOC() throws JRException {

        Exporter exporter = new JRDocxExporter();
        exporter.setExporterInput(new SimpleExporterInput(reportToPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("report.docx"));
        exporter.exportReport();
    }

    @FXML
    public void informeMostrar() throws JRException {
        JasperViewer jasperViewer = new JasperViewer(reportToPrint);
        jasperViewer.setVisible(true);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cursobetha.barcode;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.avalon.framework.configuration.Configuration;
import org.apache.avalon.framework.configuration.ConfigurationException;
import org.apache.avalon.framework.configuration.DefaultConfiguration;
import org.krysalis.barcode4j.BarcodeException;
import org.krysalis.barcode4j.BarcodeGenerator;
import org.krysalis.barcode4j.BarcodeUtil;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
/**
 *
 * @author joesu
 */
public class BarcodeBarras {

    public static void gerarCodigoBarras(String barcode) throws ConfigurationException, BarcodeException, IOException {

        BarcodeUtil util = BarcodeUtil.getInstance();
        BarcodeGenerator gen = util.createBarcodeGenerator(buildCfg("EAN-13"));

        OutputStream fout = new FileOutputStream("../standalone/deployments/ProjetoCurso-1.war/"+barcode + ".jpg");
        
        int resolution = 200;
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                fout, "image/jpeg", resolution, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        gen.generateBarcode(canvas, barcode);
        canvas.finish();
    }

    private static Configuration buildCfg(String type) {
        DefaultConfiguration cfg = new DefaultConfiguration("barcode");

        //Bar code type
        DefaultConfiguration child = new DefaultConfiguration(type);
        cfg.addChild(child);

        //Human readable text position
        DefaultConfiguration attr = new DefaultConfiguration("human-readable");
        DefaultConfiguration subAttr = new DefaultConfiguration("placement");
        subAttr.setValue("bottom");
        attr.addChild(subAttr);

        child.addChild(attr);
        return cfg;
    }

}

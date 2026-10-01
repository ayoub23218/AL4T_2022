package be.ecam;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // 1. Scan the given directory for pictures files.
        String folderPath = args.length > 0 ? args[0] : "."; // remplcer . par le chemin d'un dossier photo exemple "C:\\Users\\name\\Pictures"
        File dir = new File(folderPath);
        if (!dir.exists()) return;
        File[] files = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
            }
        });

        for (File file : files) {
            System.out.println(file.getName());
        }

        // 2. Create the UI.
        JFrame frame = new JFrame("Simple photo Viewer");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


       // Display the first picture in the array
        if (files != null && files.length > 0) {
            try {
                BufferedImage img = ImageIO.read(files[0]);

                int maxWidth = frame.getWidth();
                int maxHeight = frame.getHeight();

                int imgWidth = img.getWidth();
                int imgHeight = img.getHeight();

                double widthRatio = (double) maxWidth / imgWidth;
                double heightRatio = (double) maxHeight / imgHeight;
                double ratio = Math.min(widthRatio, heightRatio);

                int newWidth = (int) (imgWidth * ratio);
                int newHeight = (int) (imgHeight * ratio);
                java.awt.Image scaledImg = img.getScaledInstance(newWidth, newHeight, java.awt.Image.SCALE_SMOOTH);
                frame.add(new JLabel(new ImageIcon(scaledImg)), SwingConstants.CENTER);

            } catch (IOException e) {
                System.out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
            }
        } else {
            frame.add(new JLabel("Aucune image n'a été trouvée dans votre dossier Pictures."), SwingConstants.CENTER);
        }

        frame.setVisible(true);


    }
}
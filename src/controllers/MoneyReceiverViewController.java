package controllers;

import conn.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Main;
import models.Bill;
import models.OperationOfTerminal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MoneyReceiverViewController {
    private Main main;
    private Stage mainStage;

    int generalSumm = 0;
    int summa = 0;
    float sumaForClient = 0;


    ArrayList<Bill> currentList = new ArrayList<Bill>();
    OperationOfTerminal operationT;

    @FXML
    Label sumaOfClient;

    @FXML
    Label sumPaidInFact;

    public void processPayment(OperationOfTerminal operationOfTerminal){
        //operationOfTerminal = operationOfTerminal;
        sumaForClient = (float) (operationOfTerminal.getSumm() + (operationOfTerminal.getSumm() * 0.025));
        sumaOfClient.setText(sumaForClient + " ГРН.");
        operationT = operationOfTerminal;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @FXML
    public void changeMoney() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainStage);

        if(file != null){
            summa = readFilePicture(file);
            generalSumm += summa;
            sumPaidInFact.setText("");
            sumPaidInFact.setText(String.valueOf(generalSumm) + " ГРН.");
            file.delete();

        }else {
            JOptionPane.showMessageDialog(null, "Вставте купюру, будь ласка!");
        }
    }

    private int readFilePicture(File file) throws IOException {
        int summBills = 0;

        ArrayList<Bill> listBill = new ArrayList<Bill>();
        listBill.add(new Bill(ImageIO.read(new File("F:\\terminal\\money-example\\10.png")), 10));
        listBill.add(new Bill(ImageIO.read(new File("F:\\terminal\\money-example\\20.png")), 20));
        listBill.add(new Bill(ImageIO.read(new File("F:\\terminal\\money-example\\50.png")), 50));
        listBill.add(new Bill(ImageIO.read(new File("F:\\terminal\\money-example\\100.png")), 100));
        listBill.add(new Bill(ImageIO.read(new File("F:\\terminal\\money-example\\200.png")), 200));
        listBill.add(new Bill(ImageIO.read(new File("F:\\terminal\\money-example\\500.png")), 500));
        listBill.add(new Bill(ImageIO.read(new File("F:\\terminal\\money-example\\1000.png")), 1000));

        BufferedImage imgA = ImageIO.read(new File(String.valueOf(file)));
        //BufferedImage imgB = ImageIO.read(new File("F:/kiev.png"));

        /*if(bufferedImagesEqual(imgA, imgB)){
            return "деньга";
        }*/

        for(Bill element : listBill){
            if(bufferedImagesEqual(imgA, element.getImage())){
                summBills += element.getDenomination();
                currentList.add(element);
            }
        }

        return summBills;
    }

    boolean bufferedImagesEqual(BufferedImage img1, BufferedImage img2) {

        if (img1.getWidth() == img2.getWidth() && img1.getHeight() == img2.getHeight()) {
            for (int x = 0; x < img1.getWidth(); x++) {
                for (int y = 0; y < img1.getHeight(); y++) {
                    if (img1.getRGB(x, y) != img2.getRGB(x, y))
                        return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @FXML
    public void payMoneyToReceiver() throws IOException {
        if(generalSumm < sumaForClient){
            JOptionPane.showMessageDialog(null, "Недостатня сума для поповнення!");
        }else if(generalSumm == sumaForClient){
            main.switchViewFinishFirst();
        }else if(generalSumm > sumaForClient){
            //JOptionPane.showMessageDialog(null, operationT.getSumm());
            DataBaseHandler.saveOperation(operationT.numberCode, (float) operationT.summ);
            main.switchViewFinishSecond(operationT, generalSumm);
        }
    }

    @FXML
    public void exitWithMoneyReceiver() throws IOException {
        main.switchViewChange();
        if(currentList.size() != 0){
            returnBillsForUser(currentList);
        }
    }

    private void returnBillsForUser(ArrayList<Bill> currentList) throws IOException {

        String nameFolder = getDateInTransaction() + "(відмова)";
        String pathFolder = "C:\\Users\\Artem\\OneDrive\\Робочий стіл\\" + nameFolder;
        File file = new File(pathFolder);
        file.mkdir();

        for(Bill num : currentList){
            File str1 = new File("F:\\terminal\\money-example\\" + num.getDenomination() + ".png");
            File str2 = new File("C:\\Users\\Artem\\OneDrive\\Робочий стіл\\" + nameFolder + "\\" + num.getDenomination() + generatePwd(4) + ".png");

            try {
                copyFile(str1, str2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }
        if (!destFile.exists()) {
            destFile.createNewFile();
        }
        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }
    }


    private String generatePwd(int size) {
        String charsCaps = "abcdefghijklmnopqrstuvwxyz";
        String nums = "0123456789";
        String passSymbols = charsCaps + nums;
        Random rnd = new Random();

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(passSymbols.charAt(rnd.nextInt(passSymbols.length())));
        }
        return sb.toString();
    }


    private String getDateInTransaction() {
        SimpleDateFormat dnt = new SimpleDateFormat("HH-mm-ss-dd-MM-yyyy");
        Date date = new Date();

        return dnt.format(date);
    }
}

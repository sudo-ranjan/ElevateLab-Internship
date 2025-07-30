package org.example;

import com.itextpdf.io.font.constants.*;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;
import com.opencsv.CSVReader;

import java.io.*;

import com.itextpdf.kernel.font.*;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static java.lang.System.*;


class Database
{
    ///  DATABASE
    @SuppressWarnings("all")
    public static void viewDataBase()
    {
        Scanner sc = new Scanner(in);
        out.println("\nPlease enter the saved directory: ");
        StringBuilder actual_path = new StringBuilder("jdbc:sqlite:");
        StringBuilder path = new StringBuilder();
        path.append(sc.nextLine());

        out.println("\nPlease enter the DataBase File Name (only name): ");
        path.append(sc.nextLine()); path.append(".db");

        File file = new File(path.toString());
        if(!file.exists()){
            out.println("NO FILE FOUND!!"); return;
        }
        actual_path.append(path);
        out.println("FILE FOUND!!");
        DatabaseView.database_view(actual_path);
        //sc.close();
    }
}

class FieldsView
{
    public static Scanner sc = new Scanner(in);
    ///  SOME FIELDS WITH THE CONDITIONS
    @SuppressWarnings("all")
    public static StringBuilder some_fields(String table_name)
    {
        out.println("Please enter the fields to display (use comma to separate, use * for displaying all): ");
        String[] fields = new String[]{sc.nextLine()};

        StringBuilder query = new StringBuilder("SELECT ");
        for(int i=0;i<fields.length;i++){
            query.append(fields[0]);
        }
        while (query.length() > 0 && query.charAt(query.length() - 1) == ' ') {
            query.deleteCharAt(query.length() - 1);
        }
        query.append(" FROM "+table_name+" WHERE ");
        out.println("Enter the conditions with the fields (eg: ID >= 1 AND NAME == 'GULAM' ) : ");
        String conditions = new String(sc.nextLine());
        query.append(conditions);

        return query;
    }

    ///  ALL FIELDS WITH THE CONDITIONS
    @SuppressWarnings("all")
    public static StringBuilder all_fields(String table_name)
    {
        StringBuilder query = new StringBuilder("SELECT * FROM "+table_name+" WHERE ");
        out.println("Enter the conditions with the fields (eg: ID >= 1 AND NAME == 'GULAM' ) : ");
        String conditions = new String(sc.nextLine());
        query.append(conditions);
      //  sc.close();
        return query;
    }

}

class DatabaseView
{
    public static Scanner sc = new Scanner(in);
    /// CONDITIONED VIEW
    @SuppressWarnings("all")
    public static void database_view(StringBuilder path)
    {
        try
        {
            String URL = path.toString();
            Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();
            ResultSet RS = null;
            sc.nextLine();
            out.println("\nEnter the table name: ");
            String table_name = sc.nextLine();
            out.println("\n1. Display some fields with condition:\n2. Display all fields with condtion:\n3. Complete View:");
            int choice = sc.nextInt();
            if(choice == 1)
            {
                RS = stmt.executeQuery(FieldsView.some_fields(table_name).toString());
                print_data(RS);
            }
            else if (choice == 2)
            {

                RS = stmt.executeQuery(FieldsView.all_fields(table_name).toString());
                print_data(RS);
            }
            else if(choice == 3)
            {
                RS = stmt.executeQuery("SELECT * FROM "+table_name);
                print_data(RS);
            }
            else
            {
                out.println("Invalid Option!!!"); database_view(path);
            }
            conn.close();
            stmt.close();
            RS.close();
           // sc.close();

        }
        catch(SQLException e)
        {
            out.println("Oops—couldn't query your table. Did you spell it right?\n"
                    + "SQLite says: " + e.getMessage());
        }
    }

    /// DATA PRINTER
    @SuppressWarnings("all")
    public static void print_data(ResultSet RS)
    {
        try {
            ResultSetMetaData RSMD = RS.getMetaData();
            int result_columns = RSMD.getColumnCount();
            for (int i = 1; i <= 200; i++) out.print("_");
            out.println();
            for (int i = 1; i <= result_columns; i++) {
                out.printf(" | %-25s", RSMD.getColumnName(i));
            }
            out.println();
            for (int i = 1; i <= 200; i++) out.print("_");
            out.println();
            while (RS.next()) {
                for (int i = 1; i <= result_columns; i++) {
                    out.printf(" | %-25s", RS.getString(i));
                }
                out.println();
            }
            for (int i = 1; i <= 200; i++) out.print("_");
          //  sc.close();
        }
        catch(Exception e){
            out.println("Oops—couldn't query your table. Did you spell it right?\n"
                    + "SQLite says: " + e.getMessage()); e.printStackTrace();
        }
    }
}



public class Main
{
    static Scanner sc = new Scanner(in);
    @SuppressWarnings("all")
    public static void main(String[] args)
    {
        StringBuilder path = new StringBuilder();
        out.println("\n\n\n");
        for(int i=1;i<=4;i++){
            for(int j=0;j<=150;j++){
                if((i==2 || i== 3)  && j>=25 && j<=100){
                    if(i==2 && j==50) {
                        out.printf("REPORT GENERATOR");
                        j += "REPORT GENERATOR".length()-1;
                    }
                    else if(i==3 && j==50) {
                        out.printf("BY GULAM MURTUZA");
                        j += "BY GULAM MURTUZA".length() - 1;
                    }
                    else out.print(" ");
                }
                else out.print("-");
            }
            out.println();
        }

        while(true)
        {
            out.println("\n\n\n------------------------------------------------------------------------------------------------------------------------");
            out.println("\nWhat would you like to do?:");
            out.println("\n1. VIEW DATABASE\n2. VIEW EXCEL\n3. REPORT GENERATION\n4. Exit");
            out.println("\nPlease select an option: ");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    Database.viewDataBase(); break;
                case 2:
                    ViewExcel.viewExcel(); break;
                case 3:
                    try{ ReportGenerator.generate_report(); }
                    catch(Exception e){ out.println("ERROR: " + e.getMessage()); } break;
                case 4:
                    exit(0);
            }
        }
    }
}

class ViewExcel
{
    public static Scanner sc = new Scanner(in);
    /// EXCEL
    @SuppressWarnings("all")
    public static void viewExcel()
    {
        out.println("\nPlease enter the saved directory: ");
        StringBuilder path = new StringBuilder(sc.nextLine());
        out.println("\nPlease enter the Excel File Name (only name): ");
        path.append(sc.nextLine()); path.append(".csv");

        try(CSVReader reader = new CSVReader(new FileReader(path.toString())))
        {
            out.println("FILE FOUND!!");
            String[] line;
            for(int i=1;i<=200;i++) out.print("_");
            out.println();
            line = reader.readNext();
            for (String value : line) {
                out.printf(" | %-25s", value);
            }
            out.println();
            for(int i=1;i<=200;i++) out.print("_");
            out.println();
            reader.readNext();
            while((line = reader.readNext()) != null)
            {
                for (String value : line) {
                    out.printf(" | %-25s", value);
                }
                out.println();
            }
            for(int i=1;i<=200;i++) out.print("_");
            reader.close();
        }
        catch(Exception e)
        {
            out.println("ERROR: " + e.getMessage());
        }
    //    sc.close();
    }
}

class ReportGenerator
{
    public static Scanner sc = new Scanner(in);
    ///  REPORT GENERATION
    @SuppressWarnings("all")
    public static void generate_report() throws Exception
    {
        out.println("\n----------REPORT GENERATION----------\n");
        out.println("Generate report from:\n1. Database\n2. Excel File");
        int choice = sc.nextInt();

        if(choice == 1)
        {
            out.println("-------------");
            out.println("1. Generate single report:\n2. Generate multiple reports:");
            choice = sc.nextInt();
            if(choice == 1){
                SingleReportGenerator.single_report();
                sc.nextLine();
            }
            else if(choice == 2){
                MultipleReportGenerator.multiple_reports(); return;
            }
            else{
                out.println("INVALID CHOICE"); generate_report();
            }
        }
        else if(choice == 2)
        {
            out.println("NOTE: if your file has large columns then it must be upto 4 columns.");
            out.println("      The records are supported upto 60.");
            sc.nextLine();
            out.println("\nEnter the absolute path of the file (with name and extension): ");
            String path = sc.nextLine();
            out.println("Enter the header for the report: ");
            String header = sc.nextLine();
            PdfWriter writer = new PdfWriter("C:\\Users\\gulam\\Downloads\\"+header+"_"+TABLE.file_time_stamp()+".pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
            Paragraph title = TABLE.create_title(header).setFont(font);
            document.add(title);
            try(CSVReader reader = new CSVReader(new FileReader(path)))
            {
                String[] Headers = reader.readNext();

                Table table = TABLE.create_table(Headers.length);

                for(int i=0;i<Headers.length;i++)
                    table.addHeaderCell(Headers[i]).setFontSize(15).setWidth(20);

                font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
                String[] line;
                while((line = reader.readNext())!=null)
                {
                    for(String value : line){
                        table.addCell(TABLE.create_cell(value,font));
                    }
                }
                document.add(table);
                document.add(TABLE.footer());
                document.add(TABLE.time_stamp());
                document.close();
                reader.close();
                StringBuilder temp = new StringBuilder(path);
                int j = temp.length()-1;
                while(j>=0){
                    if(temp.charAt(j) == '\\') break;
                    else temp.deleteCharAt(j);
                    j--;
                }
                out.println("The File has been created and saved at "+temp);

            }
            catch(Exception e)
            {
                out.println("ERROR: " + e.getMessage());
            }
        }
        else
        {
            out.println("Invalid Option!!!"); generate_report();
        }
    }
}

class SingleReportGenerator
{
    public static Scanner sc = new Scanner(in);
    ///  SINGLE REPORT
    @SuppressWarnings("all")
    public static void single_report() throws Exception
    {
        out.println("NOTE: only the database file consisting upto 100 records and 5 fields (fields can be increased if they are short) are supported.");

        PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);

        StringBuilder path = new StringBuilder("jdbc:sqlite:");
        StringBuilder temp = new StringBuilder();
        out.println("Enter the absolute path of the file (with name and extension): ");
        temp = new StringBuilder(sc.nextLine());
        path.append(temp);

        out.println("Enter the header for the report: ");
        String header  = sc.nextLine();

        String filename = "C:\\Users\\gulam\\Downloads\\" + header + TABLE.file_time_stamp() + ".pdf";
        PdfWriter writer = new PdfWriter(filename);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        Paragraph title = TABLE.create_title(header).setFont(font);
        document.add(title);

        out.println("\nPrint?:\n1. Some fields:\n2. All fields:");
        int choice = sc.nextInt();
        out.println("Enter the table name: ");
        sc.nextLine();
        String table_name = sc.nextLine();

        Connection conn = DriverManager.getConnection(path.toString());
        Statement stmt = conn.createStatement();

        Table table = TABLE.create_table(1);


        if(choice == 1)
        {
            try(ResultSet RS = stmt.executeQuery(FieldsView.some_fields(table_name).toString()))
            {
                ResultSetMetaData RSMD = RS.getMetaData();
                int columns = RSMD.getColumnCount();
                table = TABLE.create_table(columns);

                for(int i=1;i<=columns;i++)
                    table.addHeaderCell(RSMD.getColumnName(i)).setFontSize(15).setWidth(20);

                font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
                while(RS.next()) {
                    for (int i = 1; i <= columns; i++) {
                        table.addCell(TABLE.create_cell(RS.getString(i),font));
                    }
                }
                document.add(table);
                int j = temp.length()-1;
                while(j>=0){
                    if(temp.charAt(j) == '\\') break;
                    else temp.deleteCharAt(j);
                    j--;
                }
                out.println("The File has been created and saved at "+temp);
                RS.close();
            }
            catch(SQLException e)
            {
                out.println("Oops—couldn't query your table. Did you spell it right?\n"
                        + "SQLite says: " + e.getMessage()); e.printStackTrace();
            }
        }
        else if(choice == 2)
        {
            try(ResultSet RS = stmt.executeQuery(FieldsView.all_fields(table_name).toString()))
            {
                ResultSetMetaData RSMD = RS.getMetaData();
                int columns = RSMD.getColumnCount();
                table = new Table(columns);

                for(int i=1;i<=columns;i++)
                    table.addHeaderCell(RSMD.getColumnName(i)).setFontSize(15).setWidth(20);

                font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
                while(RS.next()) {
                    for (int i = 1; i <= columns; i++) {
                        table.addCell(TABLE.create_cell(RS.getString(i),font));
                    }
                }
                document.add(table);
                int j = temp.length()-1;
                while(j>=0){
                    if(temp.charAt(j) == '\\') break;
                    else temp.deleteCharAt(j);
                    j--;
                }
                out.println("The File has been created and saved at "+temp);
                RS.close();
            }
            catch(SQLException e)
            {
                out.println("Oops—couldn't query your table. Did you spell it right?\n"
                        + "SQLite says: " + e.getMessage()); e.printStackTrace();
            }
        }
        else {
            out.println("INVALID CHOICE!!"); single_report();
        }

        document.add(TABLE.footer());
        document.add(TABLE.time_stamp());
        document.close();
        conn.close();
        stmt.close();
        writer.close();
        pdf.close();
       // sc.close();
    }
}

class MultipleReportGenerator
{
    public static Scanner sc = new Scanner(in);
    /// MULTIPLE REPORT
    @SuppressWarnings("all")
    public static void multiple_reports()
    {
        out.println("Enter the name for the reports: ");
        String name = sc.nextLine();
        File dir = new File("C:\\Users\\gulam\\Downloads\\"+name+TABLE.folder_time_stamp());
        if(!dir.mkdir()){
            out.println("\nError creating folder......\nThe Folder might already exist.\n"); multiple_reports();
        }

        StringBuilder path = new StringBuilder("jdbc:sqlite:");
        StringBuilder temp;
        out.println("\nEnter the absolute path of the DataFile (with name and extension): ");
        temp = new StringBuilder(sc.nextLine());
        path.append(temp);

        out.println("Enter the table name: ");
        String table_name = sc.nextLine();
        try(Connection conn = DriverManager.getConnection(path.toString()))
        {
            int i=1;
            Statement stmt = conn.createStatement();
            StringBuilder query  = FieldsView.some_fields(table_name);
            ResultSet RS = stmt.executeQuery(query.toString()+" LIMIT 1 OFFSET "+i);
            ResultSetMetaData RSMD = RS.getMetaData();


            int count_rows = TABLE.num_rows(conn,stmt,query.toString());
            while(i<count_rows)
            {
                RS = stmt.executeQuery(query.toString()+" LIMIT 1 OFFSET "+i);
                RSMD = RS.getMetaData();
                try
                {
                    PdfWriter writer = new PdfWriter("C:\\Users\\gulam\\Downloads\\"+name+TABLE.folder_time_stamp()+"\\"+name+TABLE.file_time_stamp()+i+".pdf");
                    PdfDocument pdf = new PdfDocument(writer);
                    Document document = new Document(pdf);

                    PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);
                    Paragraph title = TABLE.create_title(name).setFont(font);
                    document.add(title);
                    Table table = TABLE.create_table(RSMD.getColumnCount());

                    for(int j=1;j<=RSMD.getColumnCount();j++)
                        table.addHeaderCell(RSMD.getColumnName(j)).setFontSize(15).setWidth(20);

                    font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
                    while(RS.next()) {
                        for(int k=1;k<=RSMD.getColumnCount();k++) {
                            table.addCell(TABLE.create_cell(RS.getString(k),font));
                        }
                    }
                    document.add(table);
                    document.add(TABLE.footer());
                    document.add(TABLE.time_stamp());
                    document.close();
                    writer.close();
                    pdf.close();
                }
                catch(Exception e)
                {
                    out.println("ERROR: " + e.getMessage()); e.printStackTrace();
                }
                i++;
            }
            int j = temp.length()-1;
            while(j>=0){
                if(temp.charAt(j) == '\\') break;
                else temp.deleteCharAt(j);
                j--;
            }
            out.println("The folder and files has been created and saved at "+temp);
            conn.close();
            stmt.close();
            RS.close();
            // Folder to be zipped
            File sourceFolder = new File("C:\\Users\\gulam\\Downloads\\"+name+TABLE.folder_time_stamp());

            // Output ZIP file
            File zipFile = new File(sourceFolder.getParent(), sourceFolder.getName() + ".zip");

            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            zipFolder(sourceFolder, sourceFolder.getName(), zos);

            zos.close();
            fos.close();

            System.out.println("Folder successfully zipped to: " + zipFile.getAbsolutePath());
        }
        catch(SQLException | IOException e)
        {
            out.println("Oops—couldn't query your table. Did you spell it right?\n"
                    + "SQLite says: " + e.getMessage()); e.printStackTrace();
        }
    }
    public static void zipFolder(File folder, String parentFolder, ZipOutputStream zos) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                // Recursively zip subfolders
                zipFolder(file, parentFolder + "/" + file.getName(), zos);
            } else {
                FileInputStream fis = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(parentFolder + "/" + file.getName());
                zos.putNextEntry(zipEntry);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = fis.read(buffer)) >= 0) {
                    zos.write(buffer, 0, length);
                }

                zos.closeEntry();
                fis.close();
            }
        }
    }
}

class TABLE
{
    /// TITLE CREATOR
    public static Paragraph create_title(String head)
    {
        Paragraph title = new Paragraph(head)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(20);
        return title;
    }

    ///  CELL CREATOR
    public static Cell create_cell(String value,PdfFont font)
    {
        Cell dataCell = new Cell();
        dataCell.add(new Paragraph(value)).setWidth(value.length());
        dataCell.setFontSize(12).setFont(font);
        return dataCell;
    }


    /// TABLE CREATOR
    public static Table create_table(int columns)
    {
        Table table = new Table(columns);
        table.setTextAlignment(TextAlignment.CENTER);
        table.setHorizontalAlignment(HorizontalAlignment.CENTER);
        table.setVerticalAlignment(VerticalAlignment.MIDDLE);
        return table;
    }

    ///  ROWS COUNTER
    public static int num_rows(Connection conn,Statement stmt,String query)
    {
        int count = 0;
        try
        {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                count++;
            }
        }
        catch(SQLException e)
        {
            out.println("ERROR: " + e.getMessage());
        }
        return count;

    }

    /// FOOTER
    public static Paragraph footer()
    {
        Paragraph footer = new Paragraph("Generated By Gulam Murtuza")
                .setTextAlignment(TextAlignment.RIGHT).setFontSize(12);
        return footer;
    }

    /// TIME STAMP
    public static Paragraph time_stamp()
    {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd,HH:mm:ss"));
        Paragraph time_stamp = new Paragraph("Generated on "+time)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.RIGHT);
        return time_stamp;
    }

    /// FILE TIME STAMP
    public static String file_time_stamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
    }
    /// FOLDER TIME STAMP
    public static String folder_time_stamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd"));
    }
}

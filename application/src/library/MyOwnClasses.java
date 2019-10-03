/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Arkita
 */
public class MyOwnClasses {
     @SuppressWarnings("unchecked")
    
    public static class UppercaseDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
        AttributeSet attr) throws BadLocationException {

        fb.insertString(offset, text.toUpperCase(), attr);
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
        AttributeSet attrs) throws BadLocationException {
        super.remove(fb, offset, length);    
        fb.replace(offset, length, text.toUpperCase(), attrs);
        }
    }
    
    public static class PeselDocumentFilter extends DocumentFilter {
       
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
        String text, javax.swing.text.AttributeSet attr)

        throws BadLocationException {
            super.remove(fb, offset, length);
            if (fb.getDocument().getLength() + text.length() < 12)
                super.insertString(fb, offset, text.replaceAll("[^0-9]", ""), attr);
            else 
                showError();
        }
                
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) 
            throws BadLocationException {
            if (fb.getDocument().getLength() + text.length() < 12)
                super.insertString(fb, offset, text.replaceAll("[^0-9]", ""), attr);
            else 
                showError();
        }
                      
        private void showError() {
            JOptionPane.showMessageDialog(null, "Za dużo cyfr, maksymalna ilość to 11");
        }
        
    }
    
    public static class TelefonDocumentFilter extends DocumentFilter {
        
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
        String text, javax.swing.text.AttributeSet attr)
                
        throws BadLocationException {
            super.remove(fb, offset, length);
            if (fb.getDocument().getLength() + text.length() < 36)
                super.insertString(fb, offset, text.replaceAll("[^A-ZĄĆĘŁŃÓŚŹŻ0-9/+ ,-]", ""), attr);
            else 
                showError();
        }
        
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) 
            throws BadLocationException {
            if (fb.getDocument().getLength() + text.length() < 36)
                super.insertString(fb, offset, text.replaceAll("[^A-ZĄĆĘŁŃÓŚŹŻ0-9/+ ,-]", ""), attr);
            else 
                showError();
        }
                

            
        private void showError() {
            JOptionPane.showMessageDialog(null, "Za dużo znaków, maksymalna ilość to 35");
        }
    }  
    
    public static class NazwiskoDocumentFilter extends DocumentFilter {

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
        String text, javax.swing.text.AttributeSet attr) throws BadLocationException {
            super.remove(fb, offset, length);
            fb.insertString(offset, action(text), attr);                      
        }
        
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
        AttributeSet attr) throws BadLocationException {
            fb.insertString(offset, action(text), attr);           
        }
       
        String action(String text){
            String new_text;
            new_text=text.toUpperCase();
            return new_text.replaceAll("[^A-ZĄĆĘŁŃÓŚŹŻ()/, -]", "");
        }

    } 
        
    public static class AdresDocumentFilter extends DocumentFilter {

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
        String text, javax.swing.text.AttributeSet attr) throws BadLocationException {
            super.remove(fb, offset, length);
            fb.insertString(offset, action(text), attr);
        }
        
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
        AttributeSet attr) throws BadLocationException {
            fb.insertString(offset, action(text), attr);
        }
        
        String action(String text){
            String new_text;
            new_text=text.toUpperCase();
            return new_text.replaceAll("[^A-ZĄĆĘŁŃÓŚŹŻ0-9 .,-/]", "");
        }

    } 
    
    public static class ImieDocumentFilter extends DocumentFilter {

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
        String text, javax.swing.text.AttributeSet attr) throws BadLocationException {
            super.remove(fb, offset, length);
            fb.insertString(offset, action(text), attr);
        }
        
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
        AttributeSet attr) throws BadLocationException {
            fb.insertString(offset, action(text), attr);
        }
        
        String action(String text){
            String new_text;
            new_text=text.toUpperCase();
            return new_text.replaceAll("[^A-ZĆĘŁŃÓŚŹŻ()/, -]", "");
        }

    }   
    
    public static class NazwaDocumentFilter extends DocumentFilter {

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
        String text, javax.swing.text.AttributeSet attr) throws BadLocationException {
            super.remove(fb, offset, length);
            fb.insertString(offset, action(text), attr);                      
        }
        
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text,
        AttributeSet attr) throws BadLocationException {
            fb.insertString(offset, action(text), attr);           
        }
       
        String action(String text){
            String new_text;
            new_text=text.toUpperCase();
            return new_text.replaceAll("[^A-ZĆĘŁŃÓŚŹŻ()/, -]", "");
        }

    } 
     
    public static class PunktyDocumentFilter extends DocumentFilter {
       
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
        String text, javax.swing.text.AttributeSet attr)

        throws BadLocationException {
            super.remove(fb, offset, length);
            if (fb.getDocument().getLength() + text.length() < 10)
                super.insertString(fb, offset, text.replaceAll("[^0-9]", ""), attr);
            else 
                showError();
        }
                
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) 
            throws BadLocationException {
            if (fb.getDocument().getLength() + text.length() < 10)
                super.insertString(fb, offset, text.replaceAll("[^0-9]", ""), attr);
            else 
                showError();
        }
                      
        private void showError() {
            JOptionPane.showMessageDialog(null, "Za dużo cyfr, maksymalna ilość to 9");
        }
        
    }
    
    public static class CzasDocumentFilter extends DocumentFilter {
       
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
        String text, javax.swing.text.AttributeSet attr)

        throws BadLocationException {
            super.remove(fb, offset, length);
            if (fb.getDocument().getLength() + text.length() < 5)
                super.insertString(fb, offset, text.replaceAll("[^0-9.]", ""), attr);
            else 
                showError();
        }
                
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) 
            throws BadLocationException {
            if (fb.getDocument().getLength() + text.length() < 5)
                super.insertString(fb, offset, text.replaceAll("[^0-9.]", ""), attr);
            else 
                showError();
        }
                      
        private void showError() {
            JOptionPane.showMessageDialog(null, "Za dużo cyfr, maksymalna ilość to 4");
        }
        
    }
    
    public static class NumerDocumentFilter extends DocumentFilter {
       
        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length,
        String text, javax.swing.text.AttributeSet attr)

        throws BadLocationException {
            super.remove(fb, offset, length);
            if (fb.getDocument().getLength() + text.length() < 16)
                super.insertString(fb, offset, text.replaceAll("[^0-9.-/]", ""), attr);
            else 
                showError();
        }
                
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String text, AttributeSet attr) 
            throws BadLocationException {
            if (fb.getDocument().getLength() + text.length() < 16)
                super.insertString(fb, offset, text.replaceAll("[^0-9.-/]", ""), attr);
            else 
                showError();
        }
                      
        private void showError() {
            JOptionPane.showMessageDialog(null, "Za dużo cyfr, maksymalna ilość to 15");
        }
        
    }
    
    public static class VisitorRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(noFocusBorder);
            return this;
        }
    }  
    
    public static class CustomRenderer extends DefaultTableCellRenderer {
    //int col; 
    int row;
    int [] color;
    
    public CustomRenderer (int row, int [] color) //int col, 
    {
       //this.col = col;
       this.row = row;
       this.color = color;
    }
    @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component c = super.getTableCellRendererComponent
                          (table, value, isSelected, hasFocus, row, column);
            
            float[] hsb;
            Font font;
            
            if(row == 1)
            {
                font = new Font( "Calibri", Font.PLAIN, 14);
                if(color[0] == 1)                  //green
                {
                    hsb = Color.RGBtoHSB(0xe2, 0xf6, 0xd9, null);
                }
                else if(color[0] == 2)            //red
                {
                    hsb = Color.RGBtoHSB(0xf6, 0xdf, 0xd9, null);         
                }
                else
                    hsb = Color.RGBtoHSB(0xff, 0xff, 0xff, null);
            }    
            else if(row == 3)
            {
                font = new Font( "Calibri", Font.PLAIN, 14);
                if(color[1] == 1)                  //green
                {
                    hsb = Color.RGBtoHSB(0xe2, 0xf6, 0xd9, null);
                }
                else if(color[1] == 2)            //red
                {
                    hsb = Color.RGBtoHSB(0xf6, 0xdf, 0xd9, null);         
                }
                else
                    hsb = Color.RGBtoHSB(0xff, 0xff, 0xff, null);
            }                  
            else if(row == 4)
            {
                font = new Font( "Calibri", Font.PLAIN, 14);
                if(color[2] == 1)                  //green
                {
                    hsb = Color.RGBtoHSB(0xe2, 0xf6, 0xd9, null);
                }
                else if(color[2] == 2)            //red
                {
                    hsb = Color.RGBtoHSB(0xf6, 0xdf, 0xd9, null);         
                }
                else
                    hsb = Color.RGBtoHSB(0xff, 0xff, 0xff, null);
            }                  
            else if(row==0 || row==2 || row==5 || row==9 || row==13)  //gold
            {
                hsb = Color.RGBtoHSB(0xff, 0xf8, 0xdc, null);
                font = new Font( "Calibri", Font.BOLD, 14);
            }
            else  //white
            {
                hsb = Color.RGBtoHSB(0xff, 0xff, 0xff, null);
                font = new Font( "Calibri", Font.PLAIN, 14);
            }
            
            setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2])); 
            setFont(font);
            hsb = Color.RGBtoHSB(0x00, 0x00, 0x00, null);  
            setForeground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));

            return c;
        }
    }
    
    public static class InfoRenderer extends DefaultTableCellRenderer {

    int row;
    int color;
    
    public InfoRenderer (int row, int color) 
    {
       this.row = row;
       this.color = color;
    }
    @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            Component c = super.getTableCellRendererComponent
                          (table, value, isSelected, hasFocus, row, column);
            
            float[] hsb;
           
            if(row < 2)
            {
                hsb = Color.RGBtoHSB(0xf5, 0xff, 0xfa, null);
                setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));  
            } 
            //else if(row == 2)
            //{
            //    hsb = Color.RGBtoHSB(0xe1, 0xe2, 0xff, null);
            //    setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));  
            //}
            else
            {
                if(color == 1)
                    hsb = Color.RGBtoHSB(0xe2, 0xf6, 0xd9, null);
                else if(color == 2)
                    hsb = Color.RGBtoHSB(0xf6, 0xdf, 0xd9, null);  
                else
                    hsb = Color.RGBtoHSB(0xff, 0xff, 0xff, null); 
                setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
            }

            hsb = Color.RGBtoHSB(0x00, 0x00, 0x00, null);  
            setForeground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));

            return c;
        }
    }   
}
 package com.hotelmanager.ui;

// import java.awt.Graphics;
// import java.awt.Graphics2D;
// import java.awt.print.PageFormat;
// import java.awt.print.Printable;
// import java.awt.print.PrinterException;
// import java.awt.print.PrinterJob;
// import javax.swing.JPanel;

// public class MyPanel extends JPanel implements Printable {
//     // Override phương thức print() của Printable để in panel
//     @Override
//     public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
//         if (page > 0) {
//             return NO_SUCH_PAGE;
//         }

//         Graphics2D g2d = (Graphics2D) g;
//         g2d.translate(pf.getImageableX(), pf.getImageableY());

//         // Vẽ panel vào Graphics2D
//         this.printAll(g2d);

//         return PAGE_EXISTS;
//     }

//     // Phương thức in panel
//     public void printPanel() {
//         PrinterJob job = PrinterJob.getPrinterJob();
//         job.setPrintable(this);
//         try {
//             job.print();
//         } catch (PrinterException e) {
//             System.err.println(e.getMessage());
//         }
//     }
// }
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements Printable {
    // Override phương thức print() của Printable để in panel
    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Tính toán tỷ lệ giữa kích thước panel và kích thước trang giấy
        double panelWidth = this.getPreferredSize().getWidth();
        double panelHeight = this.getPreferredSize().getHeight();
        double pageWidth = pf.getImageableWidth();
        double pageHeight = pf.getImageableHeight();
        double scaleX = pageWidth / panelWidth;
        double scaleY = pageHeight / panelHeight;
        double scale = Math.min(scaleX, scaleY);

        // Căn chỉnh kích thước của panel để vừa với trang giấy
        Dimension newSize = new Dimension((int) (panelWidth * scale), (int) (panelHeight * scale));
        this.setPreferredSize(newSize);
        this.revalidate();

        // Vẽ panel vào Graphics2D
        this.printAll(g2d);

        // Khôi phục kích thước của panel về trước khi in
        this.setPreferredSize(null);
        this.revalidate();

        return PAGE_EXISTS;
    }

    // Phương thức in panel
    public void printPanel() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        try {
            job.print();
        } catch (PrinterException e) {
            System.err.println(e.getMessage());
        }
    }
}
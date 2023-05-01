package com.hotelmanager.ui;

import java.awt.*;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

import com.hotelmanager.controller.*;
import com.hotelmanager.model.Admin;
import com.hotelmanager.model.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.hotelmanager.util.Storage.*;

public class Home
{
    private JFrame frame;
    public boolean flagClose = true;

    // private JTable table;
    // private DefaultTableModel model;
    public Home()
    {
        createHomeUI();
    }

    private void createHomeUI()
    {
        frame = new JFrame("Hotel Manager - Home");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel NORTHpanel = new JPanel(new BorderLayout());
        NORTHpanel.setPreferredSize(new Dimension(1024, 100));
        NORTHpanel.setBackground(Color.LIGHT_GRAY);
        contentPanel.add(NORTHpanel, BorderLayout.NORTH);

        JPanel top_NORTHpanel = new JPanel(new GridLayout(1, 2));
        top_NORTHpanel.setPreferredSize(new Dimension(1024, 30));
        top_NORTHpanel.setBackground(Color.LIGHT_GRAY);

        top_NORTHpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel dateLabel = new JLabel();
        dateLabel.setText("Today is: " + new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        dateLabel.setHorizontalAlignment(JLabel.LEFT);

        JPanel top_NORTHpanel_Right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        top_NORTHpanel_Right.setBackground(Color.LIGHT_GRAY);

        JLabel userNameLabel = new JLabel("Hi, Admin");
        userNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        userNameLabel.setVerticalAlignment(JLabel.CENTER);
        top_NORTHpanel_Right.add(userNameLabel);

        ClassLoader cldr = Thread.currentThread().getContextClassLoader();
        URL logout_icon = cldr.getResource("logOut_icon.png");
        ImageIcon logOutButton_icon = new ImageIcon(logout_icon);
        JButton logOutButton = new JButton("LogOut", logOutButton_icon);
        logOutButton.setPreferredSize(new Dimension(102, 20));
        logOutButton.setContentAreaFilled(false);
        logOutButton.setFocusPainted(false);
        logOutButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION)
                {
                    // Đăng xuất tại đây
                    LoginUI loginUI = new LoginUI();
                    frame.setVisible(false);
                    loginUI.refresh();
                }
            }
        });

        top_NORTHpanel_Right.add(logOutButton);
        top_NORTHpanel.add(dateLabel);
        top_NORTHpanel.add(top_NORTHpanel_Right);
        NORTHpanel.add(top_NORTHpanel, BorderLayout.NORTH);

        JPanel bot_NORTHpanel = new JPanel();
        bot_NORTHpanel.setPreferredSize(new Dimension(1024, 70));
        bot_NORTHpanel.setBackground(Color.LIGHT_GRAY);
        bot_NORTHpanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 1, 1, 1, Color.BLACK),
                new EmptyBorder(10, 10, 10, 10)));
        bot_NORTHpanel.setLayout(new GridLayout(0, 6));

        URL book_icon = cldr.getResource("book_icon.png");
        ImageIcon bookButton_icon = new ImageIcon(book_icon);
        JButton bookButton = new JButton("Book", bookButton_icon);
        bookButton.setContentAreaFilled(false);
        bookButton.setFocusPainted(false);

        URL edit_icon = cldr.getResource("edit_icon.png");
        ImageIcon editButton_icon = new ImageIcon(edit_icon);
        JButton editButton = new JButton("Reservations", editButton_icon);
        // editButton.setPreferredSize(new Dimension(300,40));
        editButton.setContentAreaFilled(false);
        editButton.setFocusPainted(false);

        // ImageIcon checkoutButton_icon = new
        // ImageIcon("resources/checkout_icon.png");
        // JButton checkoutButton = new JButton("Checkout", checkoutButton_icon);
        // checkoutButton.setContentAreaFilled(false);
        // checkoutButton.setFocusPainted(false);

        URL search_icon = cldr.getResource("search_icon.png");
        ImageIcon searchButton_icon = new ImageIcon(search_icon);
        JButton searchButton = new JButton("Search", searchButton_icon);
        searchButton.setContentAreaFilled(false);
        searchButton.setFocusPainted(false);

        // ImageIcon propertyButton_icon = new
        // ImageIcon("resources/property_icon.png");
        // JButton propertyButton = new JButton("Property", propertyButton_icon);
        // propertyButton.setContentAreaFilled(false);
        // propertyButton.setFocusPainted(false);

        URL log_icon = cldr.getResource("log_icon.png");
        ImageIcon logButton_icon = new ImageIcon(log_icon);
        JButton logButton = new JButton("Log", logButton_icon);
        logButton.setContentAreaFilled(false);
        logButton.setFocusPainted(false);

        bot_NORTHpanel.add(bookButton);
        bot_NORTHpanel.add(editButton);
        // bot_NORTHpanel.add(checkoutButton);
        bot_NORTHpanel.add(searchButton);
        bot_NORTHpanel.add(new JLabel());
        bot_NORTHpanel.add(new JLabel());
        // bot_NORTHpanel.add(propertyButton);
        bot_NORTHpanel.add(logButton);
        NORTHpanel.add(bot_NORTHpanel, BorderLayout.SOUTH);
        final JPanel SOUTHpanel = new JPanel(new BorderLayout());
        contentPanel.add(SOUTHpanel, BorderLayout.CENTER);
        frame.add(contentPanel, BorderLayout.CENTER);

        PropertyController hotelProperty = new PropertyController();
        AdminController admin = new AdminController();

        ActionListener bookButtonActionListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().removeAll();
                contentPanel.remove(SOUTHpanel);
                SOUTHpanel.removeAll();

                JPanel bookJPanel_Footer = new JPanel(new GridLayout(1, 2));

                JPanel bookJPanel_Footer_Left = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JButton cancelBookPanelButton = new JButton("Cancel");

                bookJPanel_Footer_Left.add(cancelBookPanelButton);
                bookJPanel_Footer.add(bookJPanel_Footer_Left);

                JButton finishBookPanelButton = new JButton("Finish");
                JPanel bookJPanel_Footer_Right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                bookJPanel_Footer_Right.add(finishBookPanelButton);
                bookJPanel_Footer.add(bookJPanel_Footer_Right);

                JPanel bookPanel_left = new JPanel(new BorderLayout());

                JPanel bookPanel_left_top = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();

                constraints.gridx = 0;
                constraints.gridy = 0;
                bookPanel_left_top.add(new Label(), constraints);

                constraints.gridx = 0;
                constraints.gridy = 1;
                constraints.fill = GridBagConstraints.HORIZONTAL;
                JLabel customerInfoLabel = new JLabel("Customer Information");
                customerInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
                bookPanel_left_top.add(customerInfoLabel, constraints);

                constraints.gridx = 0;
                constraints.gridy = 2;
                constraints.insets = new Insets(15, 0, 0, 0);
                bookPanel_left_top.add(new JLabel("Name"), constraints);

                JTextField customerNameField = new JTextField(20);
                constraints.gridx = 1;
                constraints.gridy = 2;
                bookPanel_left_top.add(customerNameField, constraints);

                constraints.gridx = 0;
                constraints.gridy = 3;
                bookPanel_left_top.add(new JLabel("Phone Number"), constraints);

                JTextField phoneNumberField = new JTextField(20);
                constraints.gridx = 1;
                constraints.gridy = 3;
                bookPanel_left_top.add(phoneNumberField, constraints);

                constraints.gridx = 0;
                constraints.gridy = 4;
                bookPanel_left_top.add(new JLabel("Card Number "), constraints);

                JTextField cardNumberField = new JTextField(20);
                constraints.gridx = 1;
                constraints.gridy = 4;
                bookPanel_left_top.add(cardNumberField, constraints);

                constraints.gridx = 0;
                constraints.gridy = 5;
                bookPanel_left_top.add(new JLabel("Room"), constraints);

                JTextField roomField = new JTextField(20);
                constraints.gridx = 1;
                constraints.gridy = 5;
                bookPanel_left_top.add(roomField, constraints);

                constraints.gridx = 0;
                constraints.gridy = 6;
                bookPanel_left_top.add(new JLabel("Payment Method "), constraints);

                String[] options_paymentMethod = {"Cash", "Credit Card"};
                JComboBox<String> dropdown_paymentMethod = new JComboBox<>(options_paymentMethod);
                constraints.gridx = 1;
                constraints.gridy = 6;
                bookPanel_left_top.add(dropdown_paymentMethod, constraints);

                constraints.gridx = 0;
                constraints.gridy = 7;
                bookPanel_left_top.add(new JLabel("Duration"), constraints);
                JTextField durationField = new JTextField(20);
                constraints.gridx = 1;
                constraints.gridy = 7;
                bookPanel_left_top.add(durationField, constraints);

                constraints.gridx = 0;
                constraints.gridy = 8;
                bookPanel_left_top.add(new JLabel("Extras"), constraints);

                JButton addMoreButton = new JButton("Add..");
                addMoreButton.setBorderPainted(false);
                addMoreButton.setContentAreaFilled(false);
                addMoreButton.setFocusPainted(false);
                addMoreButton.setOpaque(false);
                addMoreButton.setFont(addMoreButton.getFont().deriveFont(Font.PLAIN));
                addMoreButton.setForeground(Color.BLUE);
                addMoreButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                addMoreButton.setHorizontalTextPosition(SwingConstants.CENTER);
                addMoreButton.setVerticalTextPosition(SwingConstants.BOTTOM);

                Object[] columnNames_dataExtras = {"Type", "Name", "Qty"};
                Object[][] dataExtras = {
                        // {"Food", "Bun", "2"},
                        // {"Food", "Com", "3"},
                        // {"Food", "Nuoc", "4"},
                        // {"Food", "Pho", "5"},
                        // {"Food", "Mi", "6"}
                };

                DefaultTableModel model_dataExtras = new DefaultTableModel(dataExtras, columnNames_dataExtras);

                JTable table_dataExtras = new JTable(model_dataExtras);
                table_dataExtras.setEnabled(false);

                addMoreButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        JDialog subFrame_addMore = new JDialog(frame, "Add", true);
                        subFrame_addMore.setSize(300, 300);
                        subFrame_addMore.setLocationRelativeTo(null);

                        JPanel contentPanel_addMore = new JPanel(new BorderLayout());

                        JPanel contentPanel_addMore_Main = new JPanel(new GridLayout(0, 2));
                        GridBagConstraints constraints = new GridBagConstraints();

                        constraints.gridx = 0;
                        constraints.gridy = 0;
                        constraints.fill = GridBagConstraints.HORIZONTAL;
                        JLabel Type_addMore = new JLabel("Type:");
                        contentPanel_addMore_Main.add(Type_addMore, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 1;
                        String[] options_Type_addMore = {"Food", "Drink"};
                        JComboBox<String> dropdown_Type_addMore = new JComboBox<>(options_Type_addMore);

                        contentPanel_addMore_Main.add(dropdown_Type_addMore, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 2;
                        contentPanel_addMore_Main.add(new Label(), constraints);
                        constraints.gridx = 0;
                        constraints.gridy = 3;
                        contentPanel_addMore_Main.add(new Label(), constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 4;
                        JLabel name_addMore = new JLabel("Name:");
                        contentPanel_addMore_Main.add(name_addMore, constraints);

                        constraints.gridx = 1;
                        constraints.gridy = 4;
                        JTextField name_addMoreField = new JTextField(10);
                        contentPanel_addMore_Main.add(name_addMoreField, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 5;
                        JLabel quantity_addMore = new JLabel("Quantity:");
                        contentPanel_addMore_Main.add(quantity_addMore, constraints);

                        constraints.gridx = 1;
                        constraints.gridy = 5;
                        JTextField quantity_addMoreField = new JTextField(10);
                        contentPanel_addMore_Main.add(quantity_addMoreField, constraints);


                        JPanel contentPanel_addMore_Footer = new JPanel(new GridLayout(1, 2));
                        contentPanel_addMore_Footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

                        JPanel contentPanel_addMore_Footer_Left = new JPanel(new FlowLayout(FlowLayout.LEFT));

                        JButton cancel_contentPanel_addMoreButton = new JButton("Cancel");
                        contentPanel_addMore_Footer_Left.add(cancel_contentPanel_addMoreButton);
                        contentPanel_addMore_Footer.add(contentPanel_addMore_Footer_Left);

                        cancel_contentPanel_addMoreButton.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                subFrame_addMore.setVisible(false);
                            }
                        });

                        JButton finish_contentPanel_addMoreButton = new JButton("Finish");
                        finish_contentPanel_addMoreButton.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                if (quantity_addMoreField.getText().isEmpty())
                                {
                                    JOptionPane.showMessageDialog(frame, "Please enter a quantity");
                                    return;
                                }
                                DefaultTableModel model = (DefaultTableModel) table_dataExtras.getModel();
                                Object[] newRowData = {dropdown_Type_addMore.getSelectedItem().toString(),
                                        name_addMoreField.getText(),
                                        quantity_addMoreField.getText()};
                                model.addRow(newRowData);
                                table_dataExtras.repaint();

                                // Cập nhật dữ liệu tại đây
                                JOptionPane.showMessageDialog(frame, "Extras added successfully!");
                                subFrame_addMore.setVisible(false);
                            }
                        });

                        JPanel contentPanel_addMore_Footer_Right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                        contentPanel_addMore_Footer_Right.add(finish_contentPanel_addMoreButton);
                        contentPanel_addMore_Footer.add(contentPanel_addMore_Footer_Right);

                        contentPanel_addMore.add(contentPanel_addMore_Main, BorderLayout.NORTH);
                        contentPanel_addMore.add(contentPanel_addMore_Footer, BorderLayout.SOUTH);

                        subFrame_addMore.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        subFrame_addMore.getContentPane().add(contentPanel_addMore);
                        subFrame_addMore.setVisible(true);
                    }
                });

                JPanel addMorePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                constraints.gridx = 1;
                constraints.gridy = 8;
                addMorePanel.add(addMoreButton);
                bookPanel_left_top.add(addMorePanel, constraints);

                JScrollPane bookPanel_left_bot = new JScrollPane(table_dataExtras);
                bookPanel_left_bot.setBackground(Color.LIGHT_GRAY);
                table_dataExtras.setFillsViewportHeight(false);

                bookPanel_left.add(bookPanel_left_top, BorderLayout.NORTH);
                bookPanel_left.add(bookPanel_left_bot, BorderLayout.CENTER);
                // Create bookPanel_right

                JPanel bookPanel_right = new JPanel(new BorderLayout());

                JLabel roomAlbLabel = new JLabel("Available Room");
                roomAlbLabel.setFont(new Font("Arial", Font.BOLD, 16));
                bookPanel_right.add(roomAlbLabel, BorderLayout.NORTH);

                Object[] columnNames_dataRoomAlb = {"Room no.", "Type", "Price"};
                Object[][] dataRoomAlb = hotelProperty.getAvailableRoomsObject();

                DefaultTableModel model_dataRoomAlb = new DefaultTableModel(dataRoomAlb, columnNames_dataRoomAlb);
                JTable table_dataRoomAlb = new JTable(model_dataRoomAlb);
                table_dataRoomAlb.setEnabled(false);
                table_dataRoomAlb.setFillsViewportHeight(true);

                JScrollPane tableroomAlbScrollPane = new JScrollPane(table_dataRoomAlb);
                bookPanel_right.add(tableroomAlbScrollPane, BorderLayout.CENTER);

                JSplitPane bookPanel_Main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bookPanel_left,
                        bookPanel_right);
                bookPanel_Main.setOneTouchExpandable(true);
                bookPanel_Main.setDividerLocation(frame.getWidth() / 2);

                JPanel bookPanel = new JPanel(new BorderLayout());
                bookPanel.add(bookJPanel_Footer, BorderLayout.SOUTH);
                bookPanel.add(bookPanel_Main, BorderLayout.CENTER);

                SOUTHpanel.add(bookPanel, BorderLayout.CENTER);
                contentPanel.add(SOUTHpanel, BorderLayout.CENTER);

                cancelBookPanelButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        contentPanel.remove(SOUTHpanel);
                        SOUTHpanel.removeAll();
                        contentPanel.add(SOUTHpanel, BorderLayout.CENTER);

                        contentPanel.revalidate();
                        contentPanel.repaint();
                    }
                });
                finishBookPanelButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        // Lấy dữ liệu ở đây
                        String customerName = customerNameField.getText();
                        String phoneNumberString = phoneNumberField.getText();
                        String cardNumberString = cardNumberField.getText();
                        String roomString = roomField.getText();
                        String paymentMethod = (String) dropdown_paymentMethod.getSelectedItem();
                        String durationString = durationField.getText();
                        int roomID, duration;
                        try
                        {
                            roomID = Integer.parseInt(roomString);
                            duration = Integer.parseInt(durationString);
                        }
                        catch (NumberFormatException exception)
                        {
                            JOptionPane.showMessageDialog(frame, "Non-number characters in field: \n" +
                                    exception.getMessage());
                            return;
                        }
                        if (!hotelProperty.validateRoom(roomID, dataRoomAlb))
                        {
                            JOptionPane.showMessageDialog(frame, "Please fill in a valid available room!");
                            return;
                        }
                        if (customerName.isEmpty() || phoneNumberString.isEmpty() || roomString.isEmpty() ||
                                (paymentMethod == "Credit card" && cardNumberString.isEmpty()))
                        {
                            JOptionPane.showMessageDialog(frame, "Please fill in all fields!");
                            return;
                        }
                        if (paymentMethod == "Cash" && !cardNumberString.isEmpty())
                        {
                            JOptionPane.showMessageDialog(frame, "Please clear credit card number!");
                            return;
                        }
                        try
                        {
                            admin.bookRoom(customerName, phoneNumberString, paymentMethod, cardNumberString,
                                    roomID, duration);
                        }
                        catch (Exception javaex)
                        {
                            JOptionPane.showMessageDialog(frame, javaex.getMessage());
                            return;
                        }
                        contentPanel.remove(SOUTHpanel);
                        SOUTHpanel.removeAll();
                        contentPanel.add(SOUTHpanel, BorderLayout.CENTER);
                        JOptionPane.showMessageDialog(frame, "Book successful!");
                        contentPanel.revalidate();
                        contentPanel.repaint();
                    }
                });
                frame.add(contentPanel, BorderLayout.CENTER);
                frame.revalidate(); // Cập nhật lại giao diện
                frame.repaint();
            }
        };
        bookButton.addActionListener(bookButtonActionListener);
        editButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().removeAll();
                contentPanel.remove(SOUTHpanel);
                SOUTHpanel.removeAll();

                JPanel editPanel = new JPanel(new BorderLayout());
                JPanel NORTHdeitPanel = new JPanel(new BorderLayout());
                JPanel SOUTHeditPanel = new JPanel(new GridLayout(1, 2));
                SOUTHeditPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                JPanel SOUTH_left_editPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JPanel SOUTH_right_editPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

                PropertyController prCtrl = new PropertyController();
                Object[] columnsEdit = {"ID", "Name", "Phone number", "Payment method", "Card no.", "Room",
                        "Duration", "Time booked", "Status"};
                Object[][] dataEdit = prCtrl.getReservationsObject();

                DefaultTableModel modelEdit = new DefaultTableModel(dataEdit, columnsEdit);

                JTable tableEdit = new JTable(modelEdit);
                tableEdit.setDefaultEditor(Object.class, null);
                tableEdit.getTableHeader().setReorderingAllowed(false);
                tableEdit.setRowSelectionAllowed(true);
                tableEdit.setColumnSelectionAllowed(false);

                JScrollPane scrollPaneEdit = new JScrollPane(tableEdit);
                scrollPaneEdit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                JButton editEditButton = new JButton("Edit");
                JButton checkoutEditButton = new JButton("Checkout");
                JButton cancelEditButton = new JButton("Cancel");

                NORTHdeitPanel.add(scrollPaneEdit, BorderLayout.NORTH);
                SOUTH_right_editPanel.add(editEditButton);
                SOUTH_right_editPanel.add(checkoutEditButton);
                SOUTH_left_editPanel.add(cancelEditButton);
                SOUTHeditPanel.add(SOUTH_left_editPanel);
                SOUTHeditPanel.add(SOUTH_right_editPanel);
                editPanel.add(NORTHdeitPanel, BorderLayout.CENTER);
                editPanel.add(SOUTHeditPanel, BorderLayout.SOUTH);
                SOUTHpanel.add(editPanel, BorderLayout.CENTER);
                contentPanel.add(SOUTHpanel, BorderLayout.CENTER);

                cancelEditButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        contentPanel.remove(SOUTHpanel);
                        SOUTHpanel.removeAll();

                        contentPanel.add(SOUTHpanel);
                        contentPanel.revalidate();
                        contentPanel.repaint();

                    }
                });
                checkoutEditButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        int rowtableEdit = tableEdit.getSelectedRow();
                        // get rate -> calculate total price
                        if(tableEdit.getValueAt(rowtableEdit, 8) == "Checked out")
                        {
                            JOptionPane.showMessageDialog(frame, "Already checked out!");
                            return;
                        }
                        int reservationID = Integer.parseInt(String.valueOf(tableEdit.getValueAt(rowtableEdit, 0)));
                        int totalPrice = 0;
                        try
                        {
                            totalPrice = admin.calculateTotalPrice(reservationID);
                        } catch (Exception ex)
                        {
                            JOptionPane.showMessageDialog(frame, "Failed to calculate total price.");
                            return;
                        }
                        String totalPriceString = String.valueOf(totalPrice);
                        if (rowtableEdit == -1)
                        {
                            JOptionPane.showMessageDialog(null, "Please select 1 item");
                            return;
                        }
                        int result = JOptionPane.showConfirmDialog(frame,
                                "Are you sure to checkout?" +
                                        "\nName: " + tableEdit.getValueAt(rowtableEdit, 1) + "\nRoom: "
                                        + tableEdit.getValueAt(rowtableEdit, 5) + "\nTotal: " + totalPriceString,
                                "Check-out confirmation", JOptionPane.YES_NO_OPTION);
                        if (result == JOptionPane.YES_OPTION)
                        {
                            try
                            {
                                admin.checkout(reservationID);
                            } catch (Exception ex)
                            {
                                JOptionPane.showMessageDialog(frame, "Failed to checkout: " + ex.getMessage());
                                return;
                            }
                            JDialog subFrame_Checkout = new JDialog(frame, "Checkout", true);

                            JPanel contentPanel_checkOut = new JPanel(new BorderLayout());

                            MyPanel checkout_Main = new MyPanel();
                            checkout_Main.setLayout(new BorderLayout());

                            JPanel checkout_Main_top_image = new JPanel(new GridLayout(0, 1));
                            JPanel checkout_Main_top_content = new JPanel(new GridLayout(0, 1));

                            MyPanel checkout_Main_top = new MyPanel();
                            checkout_Main_top.setLayout(new GridLayout(0, 1));
                            checkout_Main_top.setBorder(BorderFactory.createCompoundBorder(
                                    new EmptyBorder(0, 10, 0, 10), // Thêm viền trái và phải
                                    BorderFactory.createLineBorder(new Color(0, 0, 0, 0)) // Viền trong suốt
                            ));

                            URL hotel_image = cldr.getResource("hotel_image.png");
                            ImageIcon hotelIcon = new ImageIcon(hotel_image);
                            Image image = hotelIcon.getImage().getScaledInstance(170, 100, Image.SCALE_SMOOTH);
                            Icon newIcon = new ImageIcon(image);
                            JLabel hotLabel = new JLabel(newIcon);

                            checkout_Main_top_image.add(new JLabel());
                            checkout_Main_top_image.add(hotLabel);

                            JLabel title_checkout = new JLabel("Hóa đơn");
                            title_checkout.setHorizontalAlignment(JLabel.CENTER);
                            title_checkout.setVerticalAlignment(JLabel.CENTER);
                            title_checkout.setFont(new Font(title_checkout.getFont().getName(), Font.BOLD, 24));
                            checkout_Main_top_content.add(title_checkout);

                            JLabel name = new JLabel("Tên: " + tableEdit.getValueAt(rowtableEdit, 1));
                            Font font = name.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích thước
                            // mới
                            font = font.deriveFont(
                                    Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT)); // Giảm
                            // độ
                            // đậm
                            // của
                            // chữ
                            name.setFont(font);
                            checkout_Main_top_content.add(name);

                            JLabel Phone = new JLabel("SĐT: " + tableEdit.getValueAt(rowtableEdit, 2));
                            font = Phone.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích thước mới
                            font = font.deriveFont(
                                    Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT)); // Giảm
                            // độ
                            // đậm
                            // của
                            // chữ
                            Phone.setFont(font);
                            Phone.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                            checkout_Main_top_content.add(Phone);

                            JLabel room = new JLabel("Phòng: " + tableEdit.getValueAt(rowtableEdit, 5));
                            font = room.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích thước mới
                            font = font.deriveFont(
                                    Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT)); // Giảm
                            // độ
                            // đậm
                            // của
                            // chữ
                            room.setFont(font);
                            checkout_Main_top_content.add(room);

                            JLabel datein = new JLabel("Ngày đặt phòng: " + tableEdit.getValueAt(rowtableEdit, 7));
                            font = datein.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích thước mới
                            font = font.deriveFont(
                                    Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT)); // Giảm
                            // độ
                            // đậm
                            // của
                            // chữ
                            datein.setFont(font);
                            checkout_Main_top_content.add(datein);
                            LocalDateTime local = LocalDateTime.ofEpochSecond(System.currentTimeMillis() / 1000, 0, OffsetDateTime.now().getOffset());
                            String currentTime = local.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                            JLabel dateout = new JLabel("Ngày trả phòng: " + currentTime);
                            font = dateout.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích thước mới
                            font = font.deriveFont(
                                    Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT)); // Giảm
                            // độ
                            // đậm
                            // của
                            // chữ
                            dateout.setFont(font);
                            dateout.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
                            checkout_Main_top_content.add(dateout);

                            JLabel extras = new JLabel("Phụ thu: ");
                            font = extras.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích thước mới
                            font = font.deriveFont(
                                    Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT)); // Giảm
                            // độ
                            // đậm
                            // của
                            // chữ
                            extras.setFont(font);
                            checkout_Main_top_content.add(extras);
                            // if nếu có extras thì thêm extras vô theo cấu trúc như figma hoặc như tôi đã
                            // làm ở trên
                            // dưới đây tôi sẽ giả sử là có extras
                            // Tôi nghĩ nên
                            // else thì không làm gì cả

                            if (true)
                            {
                                JPanel extras_checkout = new JPanel(new GridLayout(1, 3));
                                // for (mảng extras) {
                                JLabel nameExtras = new JLabel("Tên món ");
                                font = nameExtras.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích
                                // thước mới
                                font = font.deriveFont(
                                        Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT)); // Giảm
                                // độ
                                // đậm
                                // của
                                // chữ
                                nameExtras.setFont(font);
                                extras_checkout.add(nameExtras);

                                JLabel quantity = new JLabel("Số lượng ");
                                font = quantity.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích
                                // thước mới
                                font = font.deriveFont(
                                        Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT)); // Giảm
                                // độ
                                // đậm
                                // của
                                // chữ
                                quantity.setFont(font);
                                extras_checkout.add(quantity);

                                JLabel total_extra = new JLabel("Tổng tiền ");
                                font = total_extra.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích
                                // thước mới
                                font = font.deriveFont(
                                        Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT)); // Giảm
                                // độ
                                // đậm
                                // của
                                // chữ
                                total_extra.setFont(font);
                                extras_checkout.add(total_extra);
                                // }
                                checkout_Main_top_content.add(extras_checkout);
                            }

                            JPanel total_checkout = new JPanel(new GridLayout(1, 2));
                            total_checkout.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
                            JPanel total_checkout_left = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            JPanel total_checkout_right = new JPanel(new FlowLayout(FlowLayout.CENTER));

                            JLabel title_total = new JLabel("Thành tiền: ");
                            font = title_total.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích thước
                            // mới
                            font = font.deriveFont(
                                    Collections.singletonMap(TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT)); // Giảm
                            // độ
                            // đậm
                            // của
                            // chữ
                            title_total.setFont(font);
                            total_checkout_left.add(title_total);

                            JLabel title_number = new JLabel(totalPriceString);
                            font = title_number.getFont().deriveFont(Font.BOLD, 16f); // Thiết lập kiểu chữ và kích
                            // thước mới
                            // font = font.deriveFont(Collections.singletonMap(TextAttribute.WEIGHT,
                            // TextAttribute.WEIGHT_LIGHT)); // Giảm độ đậm của chữ
                            title_number.setFont(font);
                            total_checkout_right.add(title_number);

                            total_checkout.add(total_checkout_left);
                            total_checkout.add(total_checkout_right);

                            checkout_Main_top_content.add(total_checkout);

                            checkout_Main_top.add(checkout_Main_top_image);
                            checkout_Main_top.add(checkout_Main_top_content);

                            checkout_Main.add(checkout_Main_top, BorderLayout.CENTER);

                            JPanel checkout_Footer_Panel = new JPanel(new GridLayout(1, 2));
                            checkout_Footer_Panel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

                            JPanel checkout_Footer_Panel_Left = new JPanel(new FlowLayout(FlowLayout.LEFT));
                            JPanel checkout_Footer_Panel_Right = new JPanel(new FlowLayout(FlowLayout.RIGHT));

                            JButton closeButton = new JButton("Close");
                            checkout_Footer_Panel_Left.add(closeButton);

                            JButton printButton = new JButton("Print");
                            checkout_Footer_Panel_Right.add(printButton);

                            JButton saveButton = new JButton("Save");
                            checkout_Footer_Panel_Right.add(saveButton);

                            checkout_Footer_Panel.add(checkout_Footer_Panel_Left);
                            checkout_Footer_Panel.add(checkout_Footer_Panel_Right);

                            contentPanel_checkOut.add(checkout_Main, BorderLayout.NORTH);
                            contentPanel_checkOut.add(checkout_Footer_Panel, BorderLayout.SOUTH);

                            closeButton.addActionListener(new ActionListener()
                            {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    subFrame_Checkout.setVisible(false);
                                }
                            });
                            saveButton.addActionListener(new ActionListener()
                            {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    subFrame_Checkout.setVisible(false);
                                }
                            });
                            saveButton.addActionListener(new ActionListener()
                            {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    checkout_Main_top.printPanel();
                                    // checkout_Main_top.setBorder(BorderFactory.createCompoundBorder(
                                    // new EmptyBorder(0, 50, 0, 50), // Thêm viền trái và phải
                                    // BorderFactory.createLineBorder(new Color(0, 0, 0, 0)) // Viền trong suốt
                                    // ));
                                    // subFrame_Checkout.setVisible(false);
                                }
                            });
                            printButton.addActionListener(new ActionListener()
                            {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    // Create a PrinterJob and display the print dialog
                                    PrinterJob job = PrinterJob.getPrinterJob();
                                    if (job.printDialog())
                                    {
                                        try
                                        {
                                            // Call the print() method of the Printable object to print the content
                                            job.print();
                                        } catch (PrinterException ex)
                                        {
                                            ex.printStackTrace();
                                        }
                                    }
                                }
                            });

                            subFrame_Checkout.add(contentPanel_checkOut);
                            subFrame_Checkout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            subFrame_Checkout.setSize(500, 800);
                            subFrame_Checkout.setLocationRelativeTo(null);
                            subFrame_Checkout.setResizable(false);
                            subFrame_Checkout.setVisible(true);
                        }
                    }
                });
                editEditButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        int rowtableEdit = tableEdit.getSelectedRow();
                        if (rowtableEdit == -1)
                        {
                            JOptionPane.showMessageDialog(null, "Please select 1 item");
                            return;
                        }
                        frame.getContentPane().removeAll();
                        contentPanel.remove(SOUTHpanel);
                        SOUTHpanel.removeAll();

                        JPanel bookJPanel_Footer = new JPanel(new GridLayout(1, 2));

                        JPanel bookJPanel_Footer_Left = new JPanel(new FlowLayout(FlowLayout.LEFT));
                        JButton cancelBookPanelButton = new JButton("Back");

                        bookJPanel_Footer_Left.add(cancelBookPanelButton);
                        bookJPanel_Footer.add(bookJPanel_Footer_Left);

                        JButton finishBookPanelButton = new JButton("Finish");
                        JPanel bookJPanel_Footer_Right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                        bookJPanel_Footer_Right.add(finishBookPanelButton);
                        bookJPanel_Footer.add(bookJPanel_Footer_Right);

                        JPanel bookPanel_left = new JPanel(new BorderLayout());

                        JPanel bookPanel_left_top = new JPanel(new GridBagLayout());
                        GridBagConstraints constraints = new GridBagConstraints();

                        constraints.gridx = 0;
                        constraints.gridy = 0;
                        bookPanel_left_top.add(new Label(), constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 1;
                        constraints.fill = GridBagConstraints.HORIZONTAL;
                        JLabel customerInfoLabel = new JLabel("Customer Information");
                        customerInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        bookPanel_left_top.add(customerInfoLabel, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 2;
                        constraints.insets = new Insets(15, 0, 0, 0);
                        bookPanel_left_top.add(new JLabel("Name"), constraints);

                        JTextField customerNameField = new JTextField(20);
                        customerNameField.setText(modelEdit.getValueAt(rowtableEdit, 1).toString());
                        constraints.gridx = 1;
                        constraints.gridy = 2;
                        bookPanel_left_top.add(customerNameField, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 3;
                        bookPanel_left_top.add(new JLabel("Phone Number"), constraints);

                        JTextField phoneNumberField = new JTextField(20);
                        // Phần lấy số đth từ BE
                        phoneNumberField.setText(modelEdit.getValueAt(rowtableEdit, 2).toString());
                        constraints.gridx = 1;
                        constraints.gridy = 3;
                        bookPanel_left_top.add(phoneNumberField, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 4;
                        bookPanel_left_top.add(new JLabel("Card Number"), constraints);

                        JTextField cardNumberField = new JTextField(20);
                        cardNumberField.setText(modelEdit.getValueAt(rowtableEdit, 4).toString());
                        constraints.gridx = 1;
                        constraints.gridy = 4;
                        bookPanel_left_top.add(cardNumberField, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 5;
                        bookPanel_left_top.add(new JLabel("Room"), constraints);

                        JTextField roomField = new JTextField(20);
                        roomField.setText(modelEdit.getValueAt(rowtableEdit, 5).toString());
                        constraints.gridx = 1;
                        constraints.gridy = 5;
                        bookPanel_left_top.add(roomField, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 6;
                        bookPanel_left_top.add(new JLabel("Payment Method "), constraints);

                        String[] options_paymentMethod = {"Cash", "Credit Card"};
                        JComboBox<String> dropdown_paymentMethod = new JComboBox<>(options_paymentMethod);
                        constraints.gridx = 1;
                        constraints.gridy = 6;
                        bookPanel_left_top.add(dropdown_paymentMethod, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 7;
                        bookPanel_left_top.add(new JLabel("Duration "), constraints);

                        JTextField durationField = new JTextField(20);
                        durationField.setText(modelEdit.getValueAt(rowtableEdit, 6).toString());
                        constraints.gridx = 1;
                        constraints.gridy = 7;
                        bookPanel_left_top.add(durationField, constraints);

                        constraints.gridx = 0;
                        constraints.gridy = 8;
                        bookPanel_left_top.add(new JLabel("Extras"), constraints);

                        JButton addMoreButton = new JButton("Add..");
                        addMoreButton.setBorderPainted(false);
                        addMoreButton.setContentAreaFilled(false);
                        addMoreButton.setFocusPainted(false);
                        addMoreButton.setOpaque(false);
                        addMoreButton.setFont(addMoreButton.getFont().deriveFont(Font.PLAIN));
                        addMoreButton.setForeground(Color.BLUE);
                        addMoreButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        addMoreButton.setHorizontalTextPosition(SwingConstants.CENTER);
                        addMoreButton.setVerticalTextPosition(SwingConstants.BOTTOM);

                        addMoreButton.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                JDialog subFrame_addMore = new JDialog(frame, "Add", true);
                                subFrame_addMore.setSize(300, 300);
                                subFrame_addMore.setLocationRelativeTo(null);

                                JPanel contentPanel_addMore = new JPanel(new BorderLayout());

                                JPanel contentPanel_addMore_Main = new JPanel(new GridLayout(0, 2));
                                GridBagConstraints constraints = new GridBagConstraints();

                                constraints.gridx = 0;
                                constraints.gridy = 0;
                                constraints.fill = GridBagConstraints.HORIZONTAL;
                                JLabel Type_addMore = new JLabel("Type:");
                                contentPanel_addMore_Main.add(Type_addMore, constraints);

                                constraints.gridx = 0;
                                constraints.gridy = 1;
                                String[] options_Type_addMore = {"Option 1", "Option 2", "Option 3"};
                                JComboBox<String> dropdown_Type_addMore = new JComboBox<>(options_Type_addMore);

                                contentPanel_addMore_Main.add(dropdown_Type_addMore, constraints);

                                constraints.gridx = 0;
                                constraints.gridy = 2;
                                contentPanel_addMore_Main.add(new Label(), constraints);
                                constraints.gridx = 0;
                                constraints.gridy = 3;
                                contentPanel_addMore_Main.add(new Label(), constraints);

                                constraints.gridx = 0;
                                constraints.gridy = 4;
                                JLabel name_addMore = new JLabel("Name:");
                                contentPanel_addMore_Main.add(name_addMore, constraints);

                                constraints.gridx = 1;
                                constraints.gridy = 4;
                                JTextField name_addMoreField = new JTextField(10);
                                contentPanel_addMore_Main.add(name_addMoreField, constraints);

                                constraints.gridx = 0;
                                constraints.gridy = 5;
                                JLabel quantity_addMore = new JLabel("Quantity:");
                                contentPanel_addMore_Main.add(quantity_addMore, constraints);

                                constraints.gridx = 1;
                                constraints.gridy = 5;
                                JTextField quantity_addMoreField = new JTextField(10);
                                contentPanel_addMore_Main.add(quantity_addMoreField, constraints);

                                JPanel contentPanel_addMore_Footer = new JPanel(new GridLayout(1, 2));
                                contentPanel_addMore_Footer
                                        .setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

                                JPanel contentPanel_addMore_Footer_Left = new JPanel(new FlowLayout(FlowLayout.LEFT));

                                JButton cancel_contentPanel_addMoreButton = new JButton("Cancel");
                                contentPanel_addMore_Footer_Left.add(cancel_contentPanel_addMoreButton);
                                contentPanel_addMore_Footer.add(contentPanel_addMore_Footer_Left);

                                cancel_contentPanel_addMoreButton.addActionListener(new ActionListener()
                                {
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        subFrame_addMore.setVisible(false);
                                    }
                                });

                                JButton finish_contentPanel_addMoreButton = new JButton("Finish");
                                finish_contentPanel_addMoreButton.addActionListener(new ActionListener()
                                {
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        // Cập nhật dữ liệu tại đây
                                        JOptionPane.showMessageDialog(frame, "Extras added successfully!");
                                        subFrame_addMore.setVisible(false);
                                    }
                                });

                                JPanel contentPanel_addMore_Footer_Right = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                                contentPanel_addMore_Footer_Right.add(finish_contentPanel_addMoreButton);
                                contentPanel_addMore_Footer.add(contentPanel_addMore_Footer_Right);

                                contentPanel_addMore.add(contentPanel_addMore_Main, BorderLayout.NORTH);
                                contentPanel_addMore.add(contentPanel_addMore_Footer, BorderLayout.SOUTH);

                                subFrame_addMore.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                                subFrame_addMore.getContentPane().add(contentPanel_addMore);
                                subFrame_addMore.setResizable(false);
                                subFrame_addMore.setVisible(true);
                            }
                        });

                        JPanel addMorePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                        constraints.gridx = 1;
                        constraints.gridy = 8;
                        addMorePanel.add(addMoreButton);
                        bookPanel_left_top.add(addMorePanel, constraints);

                        Object[] columnNames_dataExtras = {"Type", "Name", "Qty"};
                        Object[][] dataExtras = {
                                // {"Food", "Bun", "2"},
                                // {"Food", "Com", "3"},
                                // {"Food", "My", "1"},
                                // {"Food", "Pho", "1"},
                                // {"Food", "Chao", "3"}
                        };

                        DefaultTableModel model_dataExtras = new DefaultTableModel(dataExtras, columnNames_dataExtras);
                        JTable table_dataExtras = new JTable(model_dataExtras);
                        table_dataExtras.setEnabled(false);

                        JScrollPane bookPanel_left_bot = new JScrollPane(table_dataExtras);
                        bookPanel_left_bot.setBackground(Color.LIGHT_GRAY);
                        table_dataExtras.setFillsViewportHeight(false);

                        bookPanel_left.add(bookPanel_left_top, BorderLayout.NORTH);
                        bookPanel_left.add(bookPanel_left_bot, BorderLayout.CENTER);

                        // Create bookPanel_right

                        JPanel bookPanel_right = new JPanel(new BorderLayout());

                        JLabel roomAlbLabel = new JLabel("Available Room");
                        roomAlbLabel.setFont(new Font("Arial", Font.BOLD, 16));
                        bookPanel_right.add(roomAlbLabel, BorderLayout.NORTH);

                        PropertyController prCtrl = new PropertyController();
                        Object[] columnNames_dataRoomAlb = {"Room no.", "Type", "Price"};
                        Object[][] dataRoomAlb = prCtrl.getAvailableRoomsObject();

                        DefaultTableModel model_dataRoomAlb = new DefaultTableModel(dataRoomAlb,
                                columnNames_dataRoomAlb);
                        JTable table_dataRoomAlb = new JTable(model_dataRoomAlb);
                        table_dataRoomAlb.setEnabled(false);
                        table_dataRoomAlb.setFillsViewportHeight(true);

                        JScrollPane tableroomAlbScrollPane = new JScrollPane(table_dataRoomAlb);
                        bookPanel_right.add(tableroomAlbScrollPane, BorderLayout.CENTER);

                        JSplitPane bookPanel_Main = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, bookPanel_left,
                                bookPanel_right);
                        bookPanel_Main.setOneTouchExpandable(true);
                        bookPanel_Main.setDividerLocation(frame.getWidth() / 2);

                        JPanel bookPanel = new JPanel(new BorderLayout());
                        bookPanel.add(bookJPanel_Footer, BorderLayout.SOUTH);
                        bookPanel.add(bookPanel_Main, BorderLayout.CENTER);

                        SOUTHpanel.add(bookPanel, BorderLayout.CENTER);
                        contentPanel.add(SOUTHpanel, BorderLayout.CENTER);

                        // final JPanel SOUTHpanelCopy = SOUTHpanel;

                        cancelBookPanelButton.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                contentPanel.remove(SOUTHpanel);
                                SOUTHpanel.removeAll();
                                SOUTHpanel.add(editPanel);

                                contentPanel.add(SOUTHpanel, BorderLayout.CENTER);

                                contentPanel.revalidate();
                                contentPanel.repaint();
                            }
                        });
                        finishBookPanelButton.addActionListener(new ActionListener()
                        {
                            @Override
                            public void actionPerformed(ActionEvent e)
                            {
                                // Lấy dữ liệu ở đây
                                String customerName = customerNameField.getText();
                                String phoneNumber = phoneNumberField.getText();
                                String cardNumber = cardNumberField.getText();
                                String room = roomField.getText();
                                if (customerName.isEmpty() || phoneNumber.isEmpty() || cardNumber.isEmpty() ||
                                        room.isEmpty())
                                {
                                    JOptionPane.showMessageDialog(frame, "Please fill in all fields!");
                                    return;
                                } else
                                {
                                }
                                JOptionPane.showMessageDialog(frame, "Customer edit successfully!");
                                contentPanel.remove(SOUTHpanel);
                                SOUTHpanel.removeAll();
                                SOUTHpanel.add(editPanel);
                                contentPanel.add(SOUTHpanel, BorderLayout.CENTER);

                                contentPanel.revalidate();
                                contentPanel.repaint();
                            }
                        });

                        frame.add(contentPanel, BorderLayout.CENTER);
                        frame.revalidate(); // Cập nhật lại giao diện
                        frame.repaint();
                    }
                });

                frame.add(contentPanel, BorderLayout.CENTER);
                frame.revalidate(); // Cập nhật lại giao diện
                frame.repaint();
            }
        });
        searchButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().removeAll();
                contentPanel.remove(SOUTHpanel);
                SOUTHpanel.removeAll();

                JPanel searchPanel = new JPanel(new BorderLayout());
                JPanel searchJPanel_Footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
                searchJPanel_Footer.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

                JPanel searchPanel_Top = new JPanel(new FlowLayout());
                JPanel searchPanel_Main = new JPanel(new BorderLayout());

                String[] columnsSrch = {"Room", "Customers", "Extras"};
                Object[][] dataSrch = {
                        // { 1, "John", 25, "11" },
                        // { 2, "Sarah", 30, "12" },
                        // { 3, "Tom", 20, "13" }
                };
                DefaultTableModel modelSrch = new DefaultTableModel(dataSrch, columnsSrch);

                JTable tableSrch = new JTable(modelSrch);
                tableSrch.setDefaultEditor(Object.class, null);
                tableSrch.getTableHeader().setReorderingAllowed(false);
                tableSrch.setRowSelectionAllowed(true);
                tableSrch.setColumnSelectionAllowed(false);

                JScrollPane scrollPaneSrch = new JScrollPane(tableSrch);
                scrollPaneSrch.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                JButton cancelSearchButton = new JButton("Cancel");
                searchJPanel_Footer.add(cancelSearchButton);

                JLabel searchJLabel = new JLabel("Search:");
                JTextField searchField = new JTextField(20);
                String[] options_Type_search = {"Room", "Customers", "Extras"};
                JComboBox<String> dropdown_Type_search = new JComboBox<>(options_Type_search);
                // dropdown_Type_search.get
                searchField.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        String query = searchField.getText();
                        if (query.isEmpty())
                        {
                            JOptionPane.showMessageDialog(frame, "Please enter what you want to search");
                            return;
                        }
                        Object[][] searchData = hotelProperty.getAvailableRoomsObject();
                        Object[][] newSearchData = new Object[1][3];
                        newSearchData[0][0] = searchData[0][0];
                        newSearchData[0][1] = searchData[0][1];
                        newSearchData[0][2] = searchData[0][2];
                        String[] columnNames = {};
                        String searchType = dropdown_Type_search.getSelectedItem().toString().toLowerCase();
                        if (searchType.equals("room"))
                        {
                            columnNames = new String[]{"Room number", "Room Type", "Room Price"};
                        } else if (searchType.equals("customers"))
                        {
                            columnNames = new String[]{"Name", "Phone", "Goverment ID"};
                        } else if (searchType.equals("extras"))
                        {
                            columnNames = new String[]{"Name", "Price", "Quantity"};
                        }
                        DefaultTableModel searchTableModel = new DefaultTableModel(newSearchData,
                                columnNames);
                        tableSrch.setModel(searchTableModel);
                    }
                });

                searchPanel_Top.add(searchJLabel);
                searchPanel_Top.add(searchField);
                searchPanel_Top.add(dropdown_Type_search);
                searchPanel_Main.add(scrollPaneSrch);

                searchPanel.add(searchJPanel_Footer, BorderLayout.SOUTH);
                searchPanel.add(searchPanel_Main, BorderLayout.CENTER);
                searchPanel.add(searchPanel_Top, BorderLayout.NORTH);

                cancelSearchButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        contentPanel.remove(SOUTHpanel);
                        SOUTHpanel.removeAll();

                        contentPanel.add(SOUTHpanel);
                        contentPanel.revalidate();
                        contentPanel.repaint();
                    }
                });

                SOUTHpanel.add(searchPanel, BorderLayout.CENTER);
                contentPanel.add(SOUTHpanel, BorderLayout.CENTER);
                // JScrollPane scrollPaneSearch = new JScrollPane();
                // scrollPaneEdit.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                contentPanel.revalidate();
                contentPanel.repaint();
                frame.add(contentPanel, BorderLayout.CENTER);
                frame.revalidate(); // Cập nhật lại giao diện
                frame.repaint();
            }
        });
        logButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.getContentPane().removeAll();
                contentPanel.remove(SOUTHpanel);
                SOUTHpanel.removeAll();

                JPanel logPanel = new JPanel(new BorderLayout());
                JPanel NORTHlogPanel = new JPanel(new BorderLayout());
                JPanel SOUTHlogPanel = new JPanel(new GridLayout(1, 2));
                SOUTHlogPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                JPanel SOUTH_left_logPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JPanel SOUTH_right_logPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

                PropertyController prCtrl = new PropertyController();
                String[] columnslog = {"ID", "Description", "Date"};
                Object[][] datalog = prCtrl.getLogsObject(); //hello
                DefaultTableModel modellog = new DefaultTableModel(datalog, columnslog);

                JTable tablelog = new JTable(modellog);
                tablelog.setDefaultEditor(Object.class, null);
                tablelog.getTableHeader().setReorderingAllowed(false);
                tablelog.setRowSelectionAllowed(true);
                tablelog.setColumnSelectionAllowed(false);

                JScrollPane scrollPanelog = new JScrollPane(tablelog);
                scrollPanelog.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

                JButton cancellogButton = new JButton("Cancel");

                NORTHlogPanel.add(scrollPanelog, BorderLayout.NORTH);
                SOUTH_left_logPanel.add(cancellogButton);
                SOUTHlogPanel.add(SOUTH_left_logPanel);
                SOUTHlogPanel.add(SOUTH_right_logPanel);
                logPanel.add(NORTHlogPanel, BorderLayout.CENTER);
                logPanel.add(SOUTHlogPanel, BorderLayout.SOUTH);
                SOUTHpanel.add(logPanel, BorderLayout.CENTER);
                contentPanel.add(SOUTHpanel, BorderLayout.CENTER);

                cancellogButton.addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        contentPanel.remove(SOUTHpanel);
                        SOUTHpanel.removeAll();

                        contentPanel.add(SOUTHpanel);
                        contentPanel.revalidate();
                        contentPanel.repaint();

                    }
                });
                close();
                frame.add(contentPanel, BorderLayout.CENTER);
                frame.revalidate(); // Cập nhật lại giao diện
                frame.repaint();
            }
        });
        showFrame();
    }

    public void setHome(JFrame temp)
    {
        frame = temp;
    }

    public JFrame getHome()
    {
        return frame;
    }

    public void showFrame()
    {
        if (frame != null)
        {
            frame.setSize(new Dimension(1024, 768));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
        }
    }

    public boolean refresh()
    {
        return close();
    }

    public boolean close()
    {
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                // xử lý sự kiện khi đóng cửa sổ
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to close the app?",
                        "Confirm to close the application", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION)
                {
                    // giải phóng các tài nguyên của cửa sổ
                    System.exit(0);
                    // kết thúc ứng dụng
                } else
                    flagClose = false;
            }
        });
        return flagClose;
    }

}

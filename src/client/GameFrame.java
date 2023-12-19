package client;/*
========================================
Space Invaders Recreated
by Lloyd Torres

Published on February 2013
Updated on July 2014

Space Invaders is copyrighted by Taito Corporation.
Code provided by Lloyd on the GNU GPL v3.0 license.
========================================
Legal:

Copyright (C) 2014 Lloyd Torres

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
========================================
*/

///// MODULES TO IMPORT


import common.packets.ToClient.MessagePacket;
import common.packets.builders.MessagePacketBuilder;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.StringTokenizer;

public class GameFrame extends JFrame {
    private boolean gameStart = false;
    private Game game;
    private ConsolePanel consolePanel;

    private Client client;

    public GameFrame(String title, Client client, Game game) {
        super(title);
        this.client = client;
        this.game = game;
        setSize(game.getWidth(), game.getHeight() + 300);
        consolePanel = new ConsolePanel(this.getWidth(), 300);
        consolePanel.setInputFieldActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = consolePanel.getConsoleInput(); // You may need to add a method to retrieve the input text
                handleConsoleInput(inputText);
                consolePanel.clearInput();
                game.requestFocus();
            }
        });
//        StringTokenizer
    }

    public void start() {
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        game.setFocusable(true);
        game.requestFocusInWindow();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                client.exitProcedure();
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, game, consolePanel);
        splitPane.setResizeWeight(0);

        add(game, BorderLayout.CENTER);
        add(splitPane, BorderLayout.SOUTH);
        appendToLog("Initialized");
        setResizable(false);
        revalidate();
        repaint();

    }

    public void appendToLog(String logEntry) {
        consolePanel.appendToLog(logEntry);
    }
    public Game getGame() {
        return game;
    }

    public void handleConsoleInput(String text){
        client.sendPacket(new MessagePacketBuilder().setSenderId(client.getThisPlayer().getId()).setMessage(text).getResult());
    }



}

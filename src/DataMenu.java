import downloader.Data;
import downloader.State;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

/**
 * Created by Dima on 23.06.2017.
 */
public class DataMenu {

    private JPanel mainPanel;
    private JTextField fieldAdress;
    private JButton startPauseButton;
    private JButton stopButton;
    private JLabel stateLabel;
    private JButton resFile;
    private Data data;
    private Timer timer;
    private String textL;
    private String url;
    private JFileChooser fileChooser;

    public DataMenu() {

        fileChooser = new JFileChooser();

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    setReport();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        startPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((fileChooser.getSelectedFile() == null) || (fileChooser.getSelectedFile().getName().trim().length() == 0))
                    JOptionPane.showMessageDialog(mainPanel, "Не указан путь к файлу вывода результата, укажите путь");
                else if (data == null) {
                    url = fieldAdress.getText().trim();
                    data = new Data(Runtime.getRuntime().availableProcessors() * 2, url);
                    setActiveState();
                } else if (data.isPaused())
                    setActiveState();
                else
                    setPausedState();

            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    printUrlCall(url, fileChooser.getSelectedFile());
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
            }
        });

        resFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showDialog(null,"Выберите файл, для сохранения результата");
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setReport() throws UnsupportedEncodingException {
        State state = data.getState();
        stateLabel.setText("<html><p style=\\\"width:100px\\\">" + "-------------------------\t\nНайдено ссылок: " +
                state.getLinksDetectedCount() + "\t\n" + "Ссылок обрабатывается: " + state.getLinksInProcessCount() +
                "\t\n" + "Ссылок обработано: " + (state.getLinksDetectedCount() - state.getLinksInProcessCount()) + "\t\n" +
                "Остановлено: " + (state.isPaused() ? "да" : "нет") + "\t\n" + "Времени в работе: " + state.getTimeElapsed()/1000.0 +
                " сек." + "\t\n" + "*************************" + "\t\n" + (state.getLinksInProcessCount()==0 ? "ЗАВЕРШЕНО!!!" :
                "СКАНИРУЕТСЯ" + "</p></html>"));
        if(data.getState().getLinksInProcessCount() == 0) {
            printUrlCall(url, fileChooser.getSelectedFile());
        }
    }

    public void setActiveState() {
        startPauseButton.setText("Pause");
        timer.start();
        data.setPaused(false);
        stopButton.setEnabled(true);
        resFile.setEnabled(false);
    }

    public void setPausedState() {
        startPauseButton.setText("Start");
        data.setPaused(true);
        timer.stop();
    }

    public void printUrlCall(String url, File fileOutput) throws UnsupportedEncodingException {
        Map<String, Set<String>> urlMap;
        if (data != null) {
            data.close();
            timer.stop();
            urlMap = data.getUrlMapCopy();
            data = null;
            try (PrintWriter printWriter = new PrintWriter(fileOutput,"UTF8")) {
                printWriter.println(url);// второй вариант: если ссылка, с которой стартуем в файле вывода не нужна -
                                                                                                // эту строчку удаляем
                printUrl(url, "", urlMap, printWriter);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        stateLabel.setText("Завершено");
        textL = "";
        startPauseButton.setText("Start");
        stopButton.setEnabled(false);
        fileChooser.setSelectedFile(null);
        resFile.setEnabled(true);
    }

    public void printUrl(String url, String tab, Map<String, Set<String>> urlMap, PrintWriter printWriter) {
        Set<String> urls = urlMap.get(url);
        urlMap.remove(url);
        tab = tab + "   ";// Второй вариант: эта строчка удаляется.
        if (urls != null)
            for (String url1 : urls) {
                textL = tab + url1;
                printWriter.println(textL);
                if (urlMap.containsKey(url1)) {
                    printUrl(url1, tab + "  ", urlMap, printWriter);// Второй вариант: эта строчка меняется на:
                                                                    // printUrl(url1, tab + "  ", urlMap, printWriter);
                }
            }
    }
}



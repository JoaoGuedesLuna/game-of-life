package dev.guedes.gameoflife.views.gui;

import com.google.inject.Inject;
import dev.guedes.gameoflife.enums.ViewAction;
import dev.guedes.gameoflife.utils.swing.HtmlUtils;
import dev.guedes.gameoflife.utils.swing.LinkUtils;
import dev.guedes.gameoflife.views.View;
import dev.guedes.gameoflife.views.ViewResult;
import dev.guedes.gameoflife.views.gui.components.ComponentFactory;
import dev.guedes.gameoflife.views.gui.components.frame.Frame;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import static dev.guedes.gameoflife.views.gui.styles.GUIColors.EXPLANATION_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.EXPLANATION_CARD_BG;
import static dev.guedes.gameoflife.views.gui.styles.GUIColors.EXPLANATION_FG;
import static dev.guedes.gameoflife.views.gui.styles.GUIMetadata.APP_TITLE;
import static dev.guedes.gameoflife.views.gui.styles.GUITypography.*;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

/**
 * Rules explanation window for the program.
 *
 * @author João Guedes
 */
public class GUIExplanationView extends Frame implements View {
    private static final int CONTENT_WIDTH = 600;

    @Inject
    public GUIExplanationView() {
        super(APP_TITLE, 680, 400, false, DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.initComponents();
    }

    @Override
    public ViewResult<Void> display() { setVisible(true); return null; }

    @Override
    public ViewAction getAction() { return null; }

    private void initComponents() {
        JPanel mainPanel = createMainPanel();

        mainPanel.add(ComponentFactory.title("Explanation"));
        mainPanel.add(ComponentFactory.spacer(10));

        mainPanel.add(createText(introHtml()));
        mainPanel.add(ComponentFactory.spacer(20));

        mainPanel.add(createRulesCard());
        mainPanel.add(ComponentFactory.spacer(20));

        mainPanel.add(ComponentFactory.subtitle(("More information")));
        mainPanel.add(ComponentFactory.spacer(10));

        mainPanel.add(createText(videoHtml()));
        mainPanel.add(createText(articleHtml()));
        mainPanel.add(createText(authorHtml()));

        add(ComponentFactory.scroll(mainPanel, HORIZONTAL_SCROLLBAR_NEVER, VERTICAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(EXPLANATION_BG);
        return mainPanel;
    }

    private JPanel createRulesCard() {
        JPanel rulesCard = new JPanel();

        rulesCard.setLayout(new BoxLayout(rulesCard, BoxLayout.Y_AXIS));
        rulesCard.setBackground(EXPLANATION_CARD_BG);
        rulesCard.setBorder(new CompoundBorder(new LineBorder(new Color(80, 80, 80)), new EmptyBorder(10, 10, 10, 10)));
        rulesCard.setAlignmentX(Component.LEFT_ALIGNMENT);

        rulesCard.add(ComponentFactory.subtitle(("Game of Life Rules")));
        rulesCard.add(ComponentFactory.spacer(10));
        rulesCard.add(createText(rulesHtml()));

        return rulesCard;
    }

    private JEditorPane createText(String body) {
        String html = HtmlUtils.wrap(body, EXPLANATION_FG, FONT_FAMILY);
        JEditorPane pane = ComponentFactory.text(html, CONTENT_WIDTH );
        LinkUtils.apply(pane);
        return pane;
    }

    private String introHtml() {
        return """
            <p>
                The Game of Life is not your typical computer game.
                It is a cellular automaton, and was invented by Cambridge mathematician John Conway.
            </p>
            <p>
                This game became widely known when it was mentioned in an article published by Scientific American in 1970.
                It consists of a grid of cells which, based on a few mathematical rules, can live, die or multiply.
                Depending on the initial conditions, the cells form various patterns throughout the course of the game.
            </p>
        """;
    }

    private String rulesHtml() {
        return """
            <p>
                <b>For a space that is populated:</b><br>
                Each cell with one or no neighbors dies, as if by solitude.<br>
                Each cell with four or more neighbors dies, as if by overpopulation.<br>
                Each cell with two or three neighbors survives.
            </p>
            <p>
                <b>For a space that is empty or unpopulated:</b><br>
                Each cell with three neighbors becomes populated.
            </p>
        """;
    }

    private String videoHtml() {
        return """
            <p>
                Videos about the Game of Life
            </p>
            <ul>
                <li>
                    <a href='https://youtu.be/CgOcEZinQ2I'>The rules are explained in Stephen Hawking’s documentary The Meaning of Life</a>
                </li>
                <li>
                    <a href='https://youtu.be/R9Plq-D1gEk'>John Conway himself talks about the Game of Life</a>
                </li>
            </ul>
        """;
    }

    private String articleHtml() {
        return """
            <p>
                Interesting articles about John Conway
            </p>
            <ul>
                <li>
                    <a href='https://www.theguardian.com/science/2015/jul/23/john-horton-conway-the-most-charismatic-mathematician-in-the-world'>
                    John Horton Conway: the world’s most charismatic mathematician</a> (The Guardian)
                </li>
                <li>
                    <a href='https://www.quantamagazine.org/john-conway-solved-mathematical-problems-with-his-bare-hands-20200420/'>
                    John Conway Solved Mathematical Problems With His Bare Hands</a> (Quanta Magazine)
                </li>
            </ul>
        """;
    }

    private String authorHtml() {
        return """
            <p>
                This site is made by João Guedes &lt;<a href='mailto:joaoguedesluna@gmail.com'>joaoguedesluna@gmail.com</a>&gt</p>
            </p>
        """;
    }
}

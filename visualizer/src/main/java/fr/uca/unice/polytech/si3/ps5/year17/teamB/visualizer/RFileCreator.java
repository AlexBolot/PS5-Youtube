package fr.uca.unice.polytech.si3.ps5.year17.teamB.visualizer;

import fr.uca.unice.polytech.si3.ps5.year17.teamB.engine.utils.ArrayList8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RFileCreator {

    private int nbStrategy;
    private List<Float> scores;
    private Map<String, Double> benchmark;
    private List<List<Integer>> videoPerCache;


    public RFileCreator(int nbStrategy, List<Float> scores, Map<String, Double> benchmark, List<List<Integer>> videoPerCache) {
        this.nbStrategy = nbStrategy;
        this.scores = scores;
        this.benchmark = benchmark;
        this.videoPerCache = videoPerCache;
    }

    public String getRScript() {
        StringBuilder builder = new StringBuilder();

        builder.append("## visu.r ##\n")
                .append("library(shiny)\n")
                .append("library(shinydashboard)\n")
                .append("library(ggplot2)\n")
                .append("\n")
                .append("options(scipen=999)\n")
                .append("\n")
                .append("header <- dashboardHeader(\n")
                .append("  title = \"Collet wa mou Mosseiru\",\n")
                .append("  titleWidth = 280\n")
                .append(")\n")
                .append("\n")
                .append("\n")
                .append("sideBar <- dashboardSidebar(\n")
                .append("  sidebarMenu(\n")
                .append("    menuItem(\"Dashboard\", tabName = \"dashboard\", icon = icon(\"pie-chart\")),\n")
                .append("    menuItem(\"Execution time\", tabName = \"benchmark\", icon = icon(\"area-chart\")),\n")
                .append("    menuItem(\"Source code\", icon = icon(\"file-code-o\"), \n")
                .append("             href = \"https://mjollnir.unice.fr/bitbucket/projects/PS5F/repos/teamb/browse\")\n")
                .append("  )\n")
                .append(")\n")
                .append("\n")
                .append("body <- dashboardBody(\n")
                .append("  tabItems(\n")
                .append("    # First tab content\n")
                .append("    tabItem(tabName = \"dashboard\",\n")
                .append("            fluidRow(\n")
                .append("              tabBox(\n")
                .append("                title = \"Video in Cache for each Strategy\",\n")
                .append("                # The id lets us use input$tabset1 on the server to find the current tab\n")
                .append("                id = \"tabset1\", height = \"500px\",\n")
                .append(getNumberOfStrategy())
                .append("                width = 12\n")
                .append("              )\n")
                .append("            ),\n")
//                .append("            fluidRow(\n")
//                .append("              box(\n")
//                .append("                title = \"Total Cache used\",\n")
//                .append("                plotOutput(\"totalCache\"),\n")
//                .append("                width = 12\n")
//                .append("              )\n")
//                .append("            ),\n")
                .append("            fluidRow(\n")
                .append("              tabBox(\n")
                .append("                # Title can include an icon\n")
                .append("                title = tagList(shiny::icon(\"star\"), \"Strategy Score\"),\n")
                .append(getNumberOfStrategiesWithScore())
                .append("                width = 12\n")
                .append("              )\n")
                .append("            )\n")
                .append("    ),\n")
                .append("    \n")
                .append("    # Second tab content\n")
                .append(getBenchmarkScore())
                .append("  )\n")
                .append(")\n")
                .append("\n")
                .append("\n")
                .append("ui <- dashboardPage(\n")
                .append("  header, sideBar, body, skin = \"green\"\n")
                .append(")\n")
                .append("\n")
                .append("server <- function(input, output) {\n")
                .append("  set.seed(122)\n")
                .append("  \n")
                .append(getVideoPerCacheGraphData())
                .append("  \n")
                .append(getCacheRateForStrategiesGrahpData())
                .append("  \n")
                .append(getBenchmarkScoreGraphData())
                .append("\n")
                .append("shinyApp(ui, server)");

        return builder.toString();
    }


    private String getNumberOfStrategy() {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i <= nbStrategy; i++) {
            builder.append("                tabPanel(\"Strategy ").append(i).append("\", plotOutput(\"cacheGraph_").append(i).append("\")),\n");
        }

        return builder.toString();
    }

    private String getNumberOfStrategiesWithScore() {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i <= nbStrategy; i++) {
            builder.append("                tabPanel(\"Strategy ").append(i).append("\", h2(").append(scores.get(i - 1)).append(")),\n");
        }

        return builder.toString();
    }

    private String getBenchmarkScore() {
        StringBuilder builder = new StringBuilder();

        builder .append("    tabItem(tabName = \"benchmark\",\n")
                .append("            h2(\"Execution time (in seconds)\"),\n");


        for (String key : benchmark.keySet()) {
            builder .append("            fluidRow(\n")
                    .append("              infoBox(\"").append(key).append("\", ").append(benchmark.get(key)).append(", icon = icon(\"clock-o\"), width = 12)\n")
                    .append("            ),\n");
        }

        builder .append("            fluidRow(box(plotOutput(\"benchmarkPieChart\"), width = 12))\n")
                .append("            \n")
                .append("    )\n");

        return builder.toString();
    }

    private String getVideoPerCacheGraphData() {
        StringBuilder builder = new StringBuilder();

        for (int i = 1; i <= nbStrategy; i++) {

            StringBuilder videos = new StringBuilder();
            for (Integer integer : this.videoPerCache.get(i - 1)) {
                videos.append(integer).append(" ,");
            }

            videos.deleteCharAt(videos.length() - 1);

            builder .append("  output$cacheGraph_").append(i).append(" <- renderPlot({\n")
                    .append("    # Simple Bar Plot\n")
                    .append("    counts <- c(").append(videos.toString()).append(")\n")
                    .append("    barplot(counts,\n")
                    .append("            xlab=\"Number of Caches\") \n")
                    .append("  })\n")
                    .append("  \n");
        }

        return builder.toString();
    }

    private String getCacheRateForStrategiesGrahpData() {
        StringBuilder builder = new StringBuilder();

        StringBuilder strat = new StringBuilder();

        builder .append("  output$totalCache <- renderPlot({\n")
                .append("    # Stacked Bar Plot with Colors and Legend\n")
                .append("    counts <- table(mtcars$vs, mtcars$gear)\n")
                .append("    barplot(counts,\n")
                .append("            xlab=\"Number of Gears\", col=c(\"darkblue\",\"red\"),\n")
                .append("            legend = rownames(counts)) \n")
                .append("  })\n");

        return "";
    }

    private String getBenchmarkScoreGraphData() {
        StringBuilder builder = new StringBuilder();

        StringBuilder scores = new StringBuilder();
        StringBuilder methods = new StringBuilder();

        for (String key : benchmark.keySet()) {
            methods.append("\"").append(key).append("\" ,");
            scores.append(benchmark.get(key)).append(" ,");
        }

        methods.deleteCharAt(methods.length() - 1);
        scores.deleteCharAt(scores.length() - 1);

        builder .append("  output$benchmarkPieChart <- renderPlot({\n")
                .append("    # Pie Chart with Percentages\n")
                .append("    slices <- c(").append(scores).append(")\n")
                .append("    lbls <- c(").append(methods).append(")\n")
                .append("    pct <- round(slices/sum(slices)*100)\n")
                .append("    lbls <- paste(lbls, pct) # add percents to labels\n")
                .append("    lbls <- paste(lbls,\"%\",sep=\"\") # ad % to labels\n")
                .append("    pie(slices,labels = lbls,\n")
                .append("        main=\"Total execution time\") \n")
                .append("  })\n")
                .append("}\n");

        return builder.toString();
    }

}

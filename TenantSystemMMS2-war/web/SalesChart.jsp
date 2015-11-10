<%-- 
    Document   : SalesChart
    Created on : Nov 9, 2015, 11:55:31 AM
    Author     : AdminNUS
--%>

<%@page import="mms2.pos.entity.SaleEntity"%>
<%@page import="java.awt.Paint"%>
<%@page import="org.jfree.chart.renderer.category.CategoryItemRenderer"%>
<%@page import="java.awt.Color"%>
<%@page import="org.jfree.chart.renderer.category.BarRenderer"%>
<%@page import="org.jfree.chart.plot.CategoryPlot"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="java.io.File"%>
<%@page import="org.jfree.chart.entity.StandardEntityCollection"%>
<%@page import="org.jfree.chart.ChartRenderingInfo"%>
<%@page import="org.jfree.chart.plot.PlotOrientation"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sales Chart</title>
    </head>
    <body>
        <h1>Sales Chart</h1>
        <%
            //CUSTOM CHART
            class CustomRenderer extends BarRenderer {

                /**
                 * The colors.
                 */
                private Paint[] colors;

                /**
                 * Creates a new renderer.
                 *
                 * @param colors the colors.
                 */
                public CustomRenderer(final Paint[] colors) {
                    this.colors = colors;
                }

                /**
                 * Returns the paint for an item. Overrides the default
                 * behaviour inherited from AbstractSeriesRenderer.
                 *
                 * @param row the series.
                 * @param column the category.
                 *
                 * @return The item color.
                 */
                public Paint getItemPaint(final int row, final int column) {
                    return this.colors[column % this.colors.length];
                }
            }
            //CALCULATE TOTAL OF EACH MONTH
            ArrayList<SaleEntity> saleList = (ArrayList<SaleEntity>) request.getAttribute("saleList");
            double total = 0;
            double total0 = 0;
            double total1 = 0;
            double total2 = 0;
            double total3 = 0;
            double total4 = 0;
            double total5 = 0;
            double total6 = 0;
            double total7 = 0;
            double total8 = 0;
            double total9 = 0;
            double total10 = 0;
            double total11 = 0;
            Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
            String currentMonth = (currentTimeStamp.toString()).substring(0, 7);
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date(currentTimeStamp.getTime()));
            cal.add(Calendar.MONTH, -1);
            String currentMonthless1 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
            cal.add(Calendar.MONTH, -1);
            String currentMonthless2 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
            cal.add(Calendar.MONTH, -1);
            String currentMonthless3 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
            cal.add(Calendar.MONTH, -1);
            String currentMonthless4 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
            cal.add(Calendar.MONTH, -1);
            String currentMonthless5 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
            cal.add(Calendar.MONTH, -1);
            String currentMonthless6 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
            cal.add(Calendar.MONTH, -1);
            String currentMonthless7 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
            cal.add(Calendar.MONTH, -1);
            String currentMonthless8 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
            cal.add(Calendar.MONTH, -1);
            String currentMonthless9 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
            cal.add(Calendar.MONTH, -1);
            String currentMonthless10 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
            cal.add(Calendar.MONTH, -1);
            String currentMonthless11 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);

            for (int i = 0; i < saleList.size(); i++) {
                String datecheck = saleList.get(i).getDate().toString().substring(0, 7);

                if (datecheck.equals(currentMonth)) {
                    double amount0 = saleList.get(i).getPrice();
                    total0 = amount0 + total0;
                }
                if (datecheck.equals(currentMonthless1)) {
                    double amount1 = saleList.get(i).getPrice();
                    total1 = amount1 + total1;
                }
                if (datecheck.equals(currentMonthless2)) {
                    double amount2 = saleList.get(i).getPrice();
                    total2 = amount2 + total2;
                }
                if (datecheck.equals(currentMonthless3)) {
                    double amount3 = saleList.get(i).getPrice();
                    total3 = amount3 + total3;
                }
                if (datecheck.equals(currentMonthless4)) {
                    double amount4 = saleList.get(i).getPrice();
                    total4 = amount4 + total4;
                }
                if (datecheck.equals(currentMonthless5)) {
                    double amount5 = saleList.get(i).getPrice();
                    total5 = amount5 + total5;
                }
                if (datecheck.equals(currentMonthless6)) {
                    double amount6 = saleList.get(i).getPrice();
                    total6 = amount6 + total6;
                }
                if (datecheck.equals(currentMonthless7)) {
                    double amount7 = saleList.get(i).getPrice();
                    total7 = amount7 + total7;
                }
                if (datecheck.equals(currentMonthless8)) {
                    double amount8 = saleList.get(i).getPrice();
                    total8 = amount8 + total8;
                }
                if (datecheck.equals(currentMonthless9)) {
                    double amount9 = saleList.get(i).getPrice();
                    total9 = amount9 + total9;
                }
                if (datecheck.equals(currentMonthless10)) {
                    double amount10 = saleList.get(i).getPrice();
                    total10 = amount10 + total10;
                }
                if (datecheck.equals(currentMonthless11)) {
                    double amount11 = saleList.get(i).getPrice();
                    total11 = amount11 + total11;
                }
                Long id = saleList.get(i).getId();
                String customer = saleList.get(i).getCustomerEmail();
                String mall = saleList.get(i).getMallName();
                String date = saleList.get(i).getDate().toString().substring(0, 19);
                double amount = saleList.get(i).getPrice();
                total = amount + total;
            }
            //START CREATE PIE CHART
            final String dummy = "dummy";

            //column
            //create dataset
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            dataset.addValue(total11, dummy, currentMonthless11);
            dataset.addValue(total10, dummy, currentMonthless10);
            dataset.addValue(total9, dummy, currentMonthless9);
            dataset.addValue(total8, dummy, currentMonthless8);
            dataset.addValue(total7, dummy, currentMonthless7);
            dataset.addValue(total6, dummy, currentMonthless6);
            dataset.addValue(total5, dummy, currentMonthless5);
            dataset.addValue(total4, dummy, currentMonthless4);
            dataset.addValue(total3, dummy, currentMonthless3);
            dataset.addValue(total2, dummy, currentMonthless2);
            dataset.addValue(total1, dummy, currentMonthless1);
            dataset.addValue(total0, dummy, currentMonth);

            //create chart
            final JFreeChart chart = ChartFactory.createBarChart("Last 12 months sales", "Month", "Amount", dataset,
                    PlotOrientation.VERTICAL, false, true, false);
            final CategoryPlot plot = chart.getCategoryPlot();
            final BarRenderer renderer = new CustomRenderer(new Paint[] {Color.blue, Color.blue, Color.blue,
                Color.blue, Color.blue, Color.blue,
                Color.blue, Color.blue,Color.blue,Color.blue,Color.blue ,Color.yellow});
            plot.setRenderer(renderer);
            plot.setBackgroundPaint(Color.lightGray);

            try {
                final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
                final File file1 = new File(getServletContext().getRealPath("") + "/images/barchart.png");
                ChartUtilities.saveChartAsPNG(file1, chart, 1200, 800, info);
            } catch (Exception e) {
                System.out.println(e);
            }
        %>
        <img class="chartImage" src="${pageContext.request.contextPath}/images/barchart.png" 
             WIDTH="750" HEIGHT="500" BORDER="0"/>
        
        <br><br>
        <a href="OverallSales">Back to Overall Sales Page</a><br>
        <br>
        <a href="GoBackIndex">Back to Home Page</a><br>
    </body>
</html>

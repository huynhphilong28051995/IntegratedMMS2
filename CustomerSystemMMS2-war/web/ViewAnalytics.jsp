<%-- 
    Document   : ViewAnalytics
    Created on : Nov 5, 2015, 6:21:48 PM
    Author     : AdminNUS
--%>

<%@page import="mms2.pos.entity.SaleEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.jfree.chart.entity.StandardEntityCollection"%>
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="java.io.File"%>
<%@page import="org.jfree.chart.ChartRenderingInfo"%>
<%@page import="org.jfree.chart.labels.StandardPieSectionLabelGenerator"%>
<%@page import="java.awt.Color"%>
<%@page import="org.jfree.chart.plot.PiePlot"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ViewAnalytics</title>
    </head>
    <body>
        <!--START MAKE PIE CHART-->
        <%
            String customername = (String) request.getAttribute("customername");
            ArrayList<SaleEntity> purchaseList = (ArrayList<SaleEntity>) request.getAttribute("purchaseList");
            double total = 0;
            double numFB = 0;
            double numRT = 0;
            double numET = 0;
            double numEV = 0;
            double numUndefined = 0;
            for (int i = 0; i < purchaseList.size(); i++) {
                Long id = purchaseList.get(i).getId();
                Long tenantID = purchaseList.get(i).getTenantId();
                String businessType = purchaseList.get(i).getBusinessType();
                double price = purchaseList.get(i).getPrice();
                if (businessType.equals("Food&Beverage")) {
                    numFB = numFB + price;
                } else if (businessType.equals("Retail")) {
                    numRT = numRT + price;
                } else if (businessType.equals("Entertainment")) {
                    numET = numET + price;
                } else if (businessType.equals("Event")) {
                    numEV = numEV + price;
                } else {
                    numUndefined = numUndefined + price;
                }
                String name = purchaseList.get(i).getTenantName();
                int pointsEarned = purchaseList.get(i).getPointsEarned();
                String mall = purchaseList.get(i).getMallName();
                String date = purchaseList.get(i).getDate().toString().substring(0, 19);
                total = price + total;
            }
                    //list all content
        %>
        <h1><%=customername%>'s spending chart</h1>
        <%
            //START CALCULATE PERCENTAGE OF PROTOTYPE CATEGORY
            double numTotal = total;
            double percentFB = numFB / numTotal;
            double percentRT = numRT / numTotal;
            double percentET = numET / numTotal;
            double percentEV = numEV / numTotal;
            double percentUndefined = numUndefined / numTotal;
            //END CALCULATE PERCENTAGE OF PROTOTYPE CATEGORY
            //START CREATE PIE CHART
            DefaultPieDataset pieDataset = new DefaultPieDataset();
            pieDataset.setValue("F&B", percentFB);
            pieDataset.setValue("Retail", percentRT);
            pieDataset.setValue("Entertainment", percentET);
            pieDataset.setValue("Event", percentEV);
            pieDataset.setValue("Undefined", percentUndefined);
            JFreeChart chart = ChartFactory.createPieChart(" Customer spending analytics ",
                    pieDataset, true, true, false);
            //chart.setBackgroundPaint(new Color(222, 222, 255));
            final PiePlot plot = (PiePlot) chart.getPlot();
            plot.setBackgroundPaint(Color.white);
            plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} {2}"));
            plot.setCircular(true);
            plot.setSectionPaint("F&B", new Color(87, 210, 252));
            plot.setSectionPaint("Retail", new Color(226, 141, 142));
            plot.setSectionPaint("Entertainment", new Color(86, 182, 159));
            plot.setSectionPaint("Event", new Color(252, 248, 152));
            plot.setSectionPaint("Undefined", new Color(217, 221, 219));
            try {
                final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
                final File file1 = new File(getServletContext().getRealPath("") + "/images/piechart.png");
                ChartUtilities.saveChartAsPNG(file1, chart, 600, 400, info);
            } catch (Exception e) {
                System.out.println(e);
            }

        %>
        <img class="chartImage" src="${pageContext.request.contextPath}/images/piechart.png" 
             WIDTH="500" HEIGHT="333" BORDER="0"/>
        <!--END MAKE PIE CHART-->
        <br>
        <a href="ViewPurchaseHistory">Back to Purchase History page</a><br>
        <br>
        <br>
        <a href="GoBackIndex">Back to Home Page</a><br>

    </body>
</html>

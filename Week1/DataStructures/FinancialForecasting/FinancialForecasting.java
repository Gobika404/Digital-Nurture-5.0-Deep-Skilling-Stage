#Exercise 7 - Financial Forecasting
public class FinancialForecast {

    static double calculateForecast(double presentValue, double growthRate, int years) {

        if (years == 0) {
            return presentValue;
        }

        return calculateForecast(
                presentValue * (1 + growthRate),
                growthRate,
                years - 1
        );
    }

    public static void main(String[] args) {

        double currentValue = 10000;
        double annualGrowth = 0.08;
        int totalYears = 5;

        double futureAmount = calculateForecast(currentValue, annualGrowth, totalYears);

        System.out.printf("Predicted Value after %d years = %.2f",
                totalYears, futureAmount);
    }
}

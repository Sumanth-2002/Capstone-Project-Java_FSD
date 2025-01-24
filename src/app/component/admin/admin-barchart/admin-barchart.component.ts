import { CommonModule, isPlatformBrowser } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { AfterViewInit, Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { CanvasJS, CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';

@Component({
  selector: 'app-admin-barchart',
  standalone: true,
  imports: [HttpClientModule, CommonModule, CanvasJSAngularChartsModule],
  templateUrl: './admin-barchart.component.html',
  styleUrl: './admin-barchart.component.css'
})
export class AdminBarchartComponent implements OnInit {
  chartOptions: any = {
    title: {
      text: "Sales Over the Years"
    },
    axisX: {
      gridThickness: 0, // Disable grid lines for the X-axis
      //tickLength: 0 // Optional: Remove tick marks on the X-axis
    },
    axisY: {
      gridThickness: 0, // Disable grid lines for the Y-axis
      tickLength: 0 // Optional: Remove tick marks on the Y-axis
    },
    creditHref: '', // Disable the credit link
    creditText: '', // Hide the credit text
    data: []
  };

  constructor(private http: HttpClient, @Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit() {
    this.fetchChartData();
  }

  
  removeCredit(){}
  ngAfterViewInit() {
    if (isPlatformBrowser(this.platformId)) {
      // Only run this code in the browser
      const creditElement = document.querySelector('.canvasjs-chart-credit');
      if (creditElement) {
        creditElement.remove();
      }
    }
  }

  fetchChartData() {
    this.http.get<any>('http://localhost:9080/chart-data').subscribe(
      (data) => {
        this.chartOptions = {
          ...this.chartOptions, // Retain existing options
          data: [
            {
              type: "column",
              dataPoints: data.sales.map((sale: any) => ({
                label: sale.year.toString(),
                y: sale.amount
              }))
            }
          ]
        };

        // Trigger chart rendering manually
        setTimeout(() => {
          if (CanvasJS) {
            const chart = new CanvasJS.Chart("chartContainer", this.chartOptions);
            chart.render();
          }
        }, 0);
      },
      (error) => {
        console.error('Error fetching chart data:', error);
      }
    );
  }
}
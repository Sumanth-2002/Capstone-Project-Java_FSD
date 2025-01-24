import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CanvasJS, CanvasJSAngularChartsModule } from '@canvasjs/angular-charts';

@Component({
  selector: 'app-admin-barchart',
  standalone: true,
  imports: [HttpClientModule,CommonModule,
    CanvasJSAngularChartsModule],
  templateUrl: './admin-barchart.component.html',
  styleUrl: './admin-barchart.component.css'
})
export class AdminBarchartComponent implements OnInit{
  chartOptions: any = {
    title: {
      text: "Sales Over the Years"
    },
    data: []
  };

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.fetchChartData();
  }

  ngAfterViewInit() {
    // Remove the CanvasJS credit link after the chart is rendered
    const creditElement = document.querySelector('.canvasjs-chart-credit');
    if (creditElement) {
      creditElement.remove();
    }
  }

  fetchChartData() {
    this.http.get<any>('http://localhost:9080/chart-data').subscribe(
      (data) => {
        this.chartOptions = {
          creditHref:'',
          creditText:"",
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
            CanvasJS.Chart.prototype.render.call(this.chartOptions);
          }
        }, 0);
      },
      (error) => {
        console.error('Error fetching chart data:', error);
      }
    );
  }

}

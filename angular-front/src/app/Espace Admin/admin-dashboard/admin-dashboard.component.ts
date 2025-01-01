import { Component, ViewChild, AfterViewInit, ElementRef } from '@angular/core';
import { UserService } from '../../Acceuil General/services/user.service';
import { PatientService } from '../../Laboratoire/services/patient.service';
import { Chart, registerables } from 'chart.js';

// Enregistrement des plugins
Chart.register(...registerables);

@Component({
  selector: 'app-admin-dashboard',
  standalone: false,
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements AfterViewInit {
  @ViewChild('myChart') myChartRef!: ElementRef<HTMLCanvasElement>;
  usersCount: number = 0;
  laboratoiresCount: number = 0;
  patientsClientCount: number = 0;
  users: any[] = [];
  myChart: Chart | undefined;

  constructor(private userService: UserService, private patientService: PatientService) {}

  ngOnInit(): void {
    this.userService.getUsersCount().subscribe((count) => {
      this.usersCount = count;
      this.checkAndInitChart();
    });

    this.userService.getLaboratoiresCount().subscribe((count) => {
      this.laboratoiresCount = count;
      this.checkAndInitChart();
    });

    this.patientService.getPatientCount().subscribe((count) => {
      this.patientsClientCount = count;
      this.checkAndInitChart();
    });

    this.userService.getUsersWithRole().subscribe((users) => {
      this.users = users;
    });
  }

  ngAfterViewInit(): void {
    this.checkAndInitChart();
  }

  checkAndInitChart(): void {
    console.log('Users Count:', this.usersCount);
    console.log('Laboratoires Count:', this.laboratoiresCount);
    console.log('Patients Count:', this.patientsClientCount);

    if (this.usersCount && this.laboratoiresCount && this.patientsClientCount && this.myChartRef?.nativeElement) {
      const ctx = this.myChartRef.nativeElement.getContext('2d');
      if (ctx) {
        if (this.myChart) {
          this.myChart.destroy();
        }

        this.myChart = new Chart(ctx, {
          type: 'pie',
          data: {
            labels: ['Laboratoires', 'Utilisateurs', 'Patients'],
            datasets: [{
              label: 'Statistiques',
              data: [this.laboratoiresCount, this.usersCount - 1, this.patientsClientCount],
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)'
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)'
              ],
              borderWidth: 1
            }]
          },
          options: {
            responsive: true,
            plugins: {
              legend: {
                position: 'top',
              },
              title: {
                display: true,
                text: 'Statistiques des Utilisateurs'
              }
            }
          }
        });
      }
    }
  }
}

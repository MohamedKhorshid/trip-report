import { ElasticService } from './elastic.service';
import { MessageService } from './message.service';
import { Trip } from './trip';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TripService {

  private tripsURL = 'http://localhost:9200/trips/trip';

  constructor(
    private messageService: MessageService,
    private elastic: ElasticService
    ) { }

  getTrips(): Promise<any[]> {
    this.messageService.add('fetching all trips');
    return this.elastic.getAll('trips', 'trip');
  }

  getTrip(id: string): Promise<any> {
    this.messageService.add('fetching trip ' + id);
    return this.elastic.find('trips', 'trip', id);
  }

  private log(message: string) {
    this.messageService.add(message);
  }
}

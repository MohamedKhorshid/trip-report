import { Component, OnInit, Input } from '@angular/core';
import { Trip } from '../trip';
import { TripService } from '../trip.service';

@Component({
  selector: 'app-trip-detail',
  templateUrl: './trip-detail.component.html',
  styleUrls: ['./trip-detail.component.css']
})
export class TripDetailComponent implements OnInit {

  @Input() id: string;
  trip: Trip;

  constructor(
    private tripService: TripService
    ) { }

  ngOnInit() {
    this.getTrip();
  }

  getTrip() {
    this.tripService.getTrip(this.id).then(trip => this.trip = trip);
  }

}

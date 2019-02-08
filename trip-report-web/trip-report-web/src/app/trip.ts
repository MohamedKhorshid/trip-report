export class Trip {

  constructor(trip: any) {
    this.id = trip.id;
    this.start = new Date(trip.start);
    this.end = new Date(trip.end);
    this.captainName = trip.captainName;
    this.customerName = trip.customerName;
  }

  id: string;
  start: Date;
  end: Date;
  captainName: string;
  customerName: string;

  getDuration(): string {
    let hours: number;
    let minutes: number;
    let seconds: number;

    const duration = (this.end.getTime() - this.start.getTime()) / 1000;

    hours = Math.floor(duration / 3600);
    minutes = Math.floor((duration / 60) % 60);
    seconds = Math.floor(duration % 60);

    return (hours > 0 ? hours + 'h ' : '') +
    (minutes > 0 ? minutes + 'm ' : '') +
    (seconds > 0 ? seconds + 's ' : '');
  }
}

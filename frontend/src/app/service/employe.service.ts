import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmployeService {

  constructor() { }

  card_data:any[]  = [{
    img: "assets/images/jhonwick.jpg",
    name: "Kalyan Kodapaka",
    title: "Full stack developer at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Jhon Carter",
    title: "Angular developer at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Marshal Erikson",
    title: "DB developer at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Lilly Aldron",
    title: "Nodejs developer at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Ted Mosbey",
    title: "Team Lead at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Robin Scherbatsky",
    title: "HR Manager at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Jhon Wick",
    title: "Functional Lead at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Barney Stinson",
    title: "Delivery Manager at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Casey Wilson",
    title: "Java Developer at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Paul Shaffer",
    title: "PMO at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Ashley Benson",
    title: "scrum master at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Cornelius Peter",
    title: "HR at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Katy Perry",
    title: "Business Analyst at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Collette Wolfe",
    title: "Project Manager at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Amanda Peet ",
    title: "Delivery Head at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "James Lanham",
    title: "Senior Software Engineer at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Robert Wisdom",
    title: "Team lead at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Sam Stefanski",
    title: "Delivery Manager at onpassive "
  }, {
    img: "assets/images/jhonwick.jpg",
    name: "Haword Willeam",
    title: "Software Engineer at onpassive "
  }];;

  getEmployeeDetails(){
    return this.card_data;
  }
}

import { Component, OnInit } from '@angular/core';
import { ImageCroppedEvent } from 'ngx-image-cropper';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit {
  imageChangedEvent: any = '';
  croppedImage: any = '';
  photoUploaded:boolean = true;

  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
    this.photoUploaded = false;
  }
  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
  }
  constructor() { }

  ngOnInit(): void {
  }

}

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from "@angular/flex-layout";
import { ImageCropperModule } from 'ngx-image-cropper';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { UploadImageComponent } from './upload-profile-image/upload-image.component';
import { MaterialModule } from './material-module';
import { PostComponent } from './post/post.component';

@NgModule({
  declarations: [
    AppComponent,
    UploadImageComponent,
    PostComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    CommonModule,
    ImageCropperModule,
    MaterialModule,
    PdfViewerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  
}

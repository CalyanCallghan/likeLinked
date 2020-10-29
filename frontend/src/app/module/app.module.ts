import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from './material.module';
import { HttpClientModule } from '@angular/common/http';
import { StartPostComponent } from './start-post/start-post.component';
import { MyGroupsComponent } from './my-groups/my-groups.component';
import { AppRoutingModule } from './app-routing.module';
import { ProfileComponent } from './profile/profile.component';
import { CreatePostComponent } from './create-post/create-post.component';
import { HomepageComponent } from './homepage/homepage.component';
import { NewsComponent } from './news/news.component';
import { HeaderComponent } from './header/header.component';
import { FormsModule } from '@angular/forms'; 
import { ChatComponent } from './chat/chat.component';
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NotificationComponent } from './notification/notification.component';
import { HighlightDirective } from './notification/highlight.directive';
import { GroupsComponent } from './groups/groups.component';
import { PersonPostComponent } from './person-post/person-post.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { FlexLayoutModule } from "@angular/flex-layout";
import { ImageCropperModule } from 'ngx-image-cropper';
import { UploadImageComponent } from './upload-profile-image/upload-image.component';
import { NgMatSearchBarModule } from 'ng-mat-search-bar';



@NgModule({
  declarations: [
    AppComponent,
    StartPostComponent,
    MyGroupsComponent,
    ProfileComponent,
    CreatePostComponent,
    HomepageComponent,
    NewsComponent,
    HeaderComponent,
    ChatComponent,
    NotificationComponent,
    HighlightDirective,
    GroupsComponent,
    PersonPostComponent,
    GroupsComponent,
    EmployeeListComponent,
    UploadImageComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    MaterialModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    MDBBootstrapModule,
    PdfViewerModule,
    FlexLayoutModule,
    ImageCropperModule,
    NgMatSearchBarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

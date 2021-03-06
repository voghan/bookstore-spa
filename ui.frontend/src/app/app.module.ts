/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2020 Adobe Systems Incorporated
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

import { SpaAngularEditableComponentsModule } from '@adobe/aem-angular-editable-components';
import { APP_BASE_HREF } from '@angular/common';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import './components/import-components';
import { ModelManagerService } from './components/model-manager.service';
import { PageComponent } from './components/page/page.component';
import { TextComponent } from './components/text/text.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { ImageComponent } from './components/image/image.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { CustomComponent } from './components/custom/custom.component';
import { CardComponent } from './components/card/card.component';
import { TeaserComponent } from './components/teaser/teaser.component';
import { TitleComponent } from './components/title/title.component';
import { SeparatorComponent } from './components/separator/separator.component';
import { ListComponent } from './components/list/list.component';
import { ButtonComponent } from './components/button/button.component';
import { TabsComponent } from './components/tabs/tabs.component';
import { EmbedComponent } from './components/embed/embed.component';
import { LinkComponent } from './components/link/link.component';
import { ArticleComponent } from './components/article/article.component';
import { LoginComponent } from './components/login/login.component';
import { MemberComponent } from './components/member/member.component';

@NgModule({
  imports: [
    BrowserModule,
    SpaAngularEditableComponentsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [ModelManagerService, { provide: APP_BASE_HREF, useValue: '/' }],
  declarations: [
    AppComponent,
    TextComponent,
    PageComponent,
    HeaderComponent,
    FooterComponent,
    ImageComponent,
    NavigationComponent,
    CustomComponent,
    CardComponent,
    TeaserComponent,
    TitleComponent,
    SeparatorComponent,
    ButtonComponent,
    ListComponent,
    TabsComponent,
    EmbedComponent,
    LinkComponent,
    ArticleComponent,
    LoginComponent,
    MemberComponent],
  entryComponents: [
    TextComponent,
    PageComponent,
    ImageComponent,
    HeaderComponent,
    CustomComponent,
    TeaserComponent,
    ButtonComponent,
    ListComponent,
    TabsComponent,
    EmbedComponent,
    LinkComponent,
    ArticleComponent],
  bootstrap: [AppComponent]
})
export class AppModule {}

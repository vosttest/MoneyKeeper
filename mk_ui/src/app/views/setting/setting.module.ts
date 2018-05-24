import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { TimepickerModule, BsDatepickerModule } from 'ngx-bootstrap';

import { SettingRoutingModule } from './setting-routing.module';
import { SettingComponent } from './setting.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { FilterPipeSetting } from '../../utilities/utility';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        TimepickerModule.forRoot(),
        BsDatepickerModule.forRoot(),
        ModalModule.forRoot(),
        SettingRoutingModule
    ],
    declarations: [
        SettingComponent,
        FilterPipeSetting
    ]
})

export class SettingModule { }
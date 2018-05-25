import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AccountAddComponent } from './account-add.component';

const routes: Routes = [
    {
        path: '',
        component: AccountAddComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class AccountAddRoutingModule { }
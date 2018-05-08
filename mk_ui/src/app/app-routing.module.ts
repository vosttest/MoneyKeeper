import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SignInComponent } from './views/sign-in/sign-in.component';
import { SignUpComponent } from './views/sign-up/sign-up.component';
import { CategoryComponent } from './views/category/category.component';
import { ManageAccountComponent } from './views/manage-account/manage-account.component';
import { ManageAccountAddComponent } from './views/manage-account-add/manage-account-add.component';
import { ManageAccountEditComponent } from './views/manage-account-edit/manage-account-edit.component';

const routes: Routes = [
    { path: '', redirectTo: 'signIn', pathMatch: 'full' },
    { path: 'signIn', component: SignInComponent },
    { path: 'signUp', component: SignUpComponent },
    { path: 'category', component: CategoryComponent },
    { path: 'insertAcc', component: ManageAccountComponent },
    { path: 'addAcc', component: ManageAccountAddComponent },
    { path: 'editAcc', component: ManageAccountEditComponent },
    { path: '**', redirectTo: 'signIn', pathMatch: 'full' },
];

@NgModule({
    imports: [RouterModule.forRoot(routes, { useHash: true })],
    exports: [RouterModule]
})

export class AppRoutingModule { }
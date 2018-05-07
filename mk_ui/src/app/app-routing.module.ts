import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { SignInComponent } from './views/sign-in/sign-in.component';

const routes: Routes = [
    { path: '', redirectTo: 'signIn', pathMatch: 'full' },
    { path: 'signIn', component: SignInComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }
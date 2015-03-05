{>"/layouts/main"/}

{<header1Content}{@i18n key="header1"/}{/header1Content}

{<menubarSecond}
<a class="button goToFilterButton" href="#filter-view" title="{@i18n key="filter-view.goto-redefine-filter.button.title"/}"><span class="icon goto-filter"> </span> <span class="label">{@i18n key="filter-view.goto-redefine-filter.button"/}</span></a>
<a class="button goToCreateButton" href="#create-view" title="{@i18n key="create-view.goto-create-view.button.title"/}"><span class="icon goto-create"> </span> <span class="label">{@i18n key="create-view.goto-create-view.button"/}</span></a>
{/menubarSecond}

{<pageContent}
<div id="search-view-result" class="panel-search-result">
	<ul class="messages"></ul>
	<div class="searchResultTable">
		<table class="result-list">
			<thead>
				<tr>
					<th scope="col" class="col-text"></th>
					<th scope="col" class="col-text"><a class="order_link" href="#">{@i18n key="table.title.code"/}</a></th>
					<th scope="col" class="col-date"><a class="order_link" href="#">{@i18n key="table.title.checkin"/}</a></th>
					<th scope="col" class="col-date"><a class="order_link" href="#">{@i18n key="table.title.checkout"/}</a></th>
					<th scope="col" class="col-number"><a class="order_link" href="#">{@i18n key="table.title.nights"/}</a></th>
					<th scope="col" class="col-number"><a class="order_link" href="#">{@i18n key="table.title.rooms"/}</a></th>
					<th scope="col" class="col-number"><a class="order_link" href="#">{@i18n key="table.title.price"/}</a></th>
					<th scope="col" class="col-icons"></th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
		<div class="pagination"></div>
	</div>
</div>

<div id="filter-view" class="popupModalDialog">
	<div>
		<a href="#close" title="{@i18n key="popupModalDialog.close.title"/}" class="close">X</a>
		<h1>{@i18n key="filter-view.title"/}</h1>
		<ul class="messages"></ul>
		<form action="">
			<div class="body">
				<div class="common-form">
					<div class="form-field-group">
						<div class="form-field input-date">
							<label for="filter-view-checkin">{@i18n key="filter-view.checkin.label"/} :</label>
							<input id="filter-view-checkin" name="checkin"/>
							<p class="field-tip">{@i18n key="filter-view.checkin.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="filter-view-code">{@i18n key="filter-view.code.label"/} :</label>
							<input id="filter-view-code" name="code" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="filter-view.code.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="filter-view-identificationCard">{@i18n key="filter-view.identificationCard.label"/} :</label>
							<input id="filter-view-identificationCard" name="identificationCard" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="filter-view.identificationCard.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="filter-view-name">{@i18n key="filter-view.name.label"/} :</label>
							<input id="filter-view-name" name="name" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="filter-view.name.tip"/}</p>
						</div>
					</div>
				</div>
			</div>
			<div class="bottom-toolbar">
				<a title="{@i18n key="filter-view.execute-filter.button.title"/}" class="button executeFilterButton">
					<span class="icon filter"> </span>
					<span class="label">{@i18n key="filter-view.execute-filter.button"/}</span>
				</a>
				<a title="{@i18n key="close.button.title"/}" href="#close" class="button closeButton">
					<span class="icon close-popup"> </span>
					<span class="label">{@i18n key="close.button"/}</span>
				</a>
			</div>
		</form>
	</div>
</div>

<div id="create-view" class="popupModalDialog">
	<div style="width: 970px;">
		<a href="#close" title="{@i18n key="popupModalDialog.close.title"/}" class="close">X</a>
		<h1>{@i18n key="create-view.title"/}</h1>
		<ul class="messages"></ul>
		<form action="">
			<div class="body tabContainer">
				<div class="tabs">
					<ul>
						<li data-content="create-view-booking-tab">{@i18n key="create-view.tab-rooms.title"/}</li>
						<li data-content="create-view-requests-tab">{@i18n key="create-view.tab-requests.title"/}</li>
						<li data-content="create-view-contact-tab">{@i18n key="create-view.tab-contact.title"/}</li>
						<li data-content="create-view-creditcard-tab">{@i18n key="create-view.tab-creditcard.title"/}</li>
					</ul>
				</div>
				<div class="common-form tabContent" id="create-view-booking-tab">
					<div class="form-field-group">
						<div class="form-field input-date">
							<label for="create-view-checkin">{@i18n key="create-view.checkin.label"/} :</label>
							<input id="create-view-checkin" name="checkin"/>
							<p class="field-tip">{@i18n key="create-view.checkin.tip"/}</p>
						</div>
						<div class="form-field input-date">
							<label for="create-view-checkout">{@i18n key="create-view.checkout.label"/} :</label>
							<input id="create-view-checkout" name="checkout"/>
							<p class="field-tip">{@i18n key="create-view.checkout.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="create-view-nights">{@i18n key="create-view.nights.label"/} :</label>
							<input id="create-view-nights" name="nights" style="width: 5em;"/>
							<p class="field-tip">{@i18n key="create-view.nights.tip"/}</p>
						</div>
					</div>
					<div class="form-field-group" style="width: 580px;">
						<table id="create-view-bookings" class="result-list">
							<thead>
								<tr>
									<th scope="col" class="col-icons">
											<a title="{@i18n key="create-view.goto-add-room.button.title"/}" class="button gotoAddRoom">
												<span class="icon goto-add-room"> </span>
											</a>
									</th>
									<th scope="col" class="col-text">{@i18n key="create-view.table.title.type"/}</th>
									<th scope="col" class="col-text">{@i18n key="create-view.table.title.name"/}</th>
									<th scope="col" class="col-number">{@i18n key="create-view.table.title.adults"/} / {@i18n key="create-view.table.title.children"/}</th>
									<th scope="col" class="col-number">{@i18n key="create-view.table.title.price"/}</th>
								</tr>
							</thead>
							<tfoot>
							<tr>
								<td colspan="4"></td>
								<td id="create-view-total-price" class="col-number">0</td>
							</tr>
							</tfoot>
							<tbody></tbody>
						</table>
					</div>
				</div>
				
				<div class="common-form tabContent" id="create-view-requests-tab">
					<div class="form-field-group">
						<div class="form-field long-text">
							<label for="create-view-specialRequest">{@i18n key="create-view.specialRequest.label"/} :</label>
							<p class="field-tip">{@i18n key="create-view.specialRequest.tip"/}</p>
							<textarea id="create-view-specialRequest" name="specialRequest" style="width: 20em; height: 20em;"> </textarea>
						</div>
					</div>
					<div class="form-field-group">
						<div class="form-field long-text">
							<label for="create-view-notes">{@i18n key="create-view.notes.label"/} :</label>
							<p class="field-tip">{@i18n key="create-view.notes.tip"/}</p>
							<textarea id="create-view-notes" name="notes" style="width: 20em; height: 20em;"> </textarea>
						</div>
					</div>
				</div>
				
				<div class="common-form tabContent" id="create-view-contact-tab">
					<div class="form-field-group">
						<div class="form-field input-text">
							<label for="create-view-firstName">{@i18n key="create-view.firstName.label"/} :</label>
							<input id="create-view-firstName" name="firstName" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="create-view.firstName.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="create-view-secondName">{@i18n key="create-view.secondName.label"/} :</label>
							<input id="create-view-secondName" name="secondName" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="create-view.secondName.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="create-view-idNumber">{@i18n key="create-view.idNumber.label"/} :</label>
							<input id="create-view-idNumber" name="idNumber" style="width: 20em;" maxlength="20"/>
							<p class="field-tip">{@i18n key="create-view.idNumber.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="create-view-email">{@i18n key="create-view.email.label"/} :</label>
							<input id="create-view-email" name="email" style="width: 20em;" />
							<p class="field-tip">{@i18n key="create-view.email.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="create-view-phone">{@i18n key="create-view.phone.label"/} :</label>
							<input id="create-view-phone" name="phone" style="width: 20em;" maxlength="15"/>
							<p class="field-tip">{@i18n key="create-view.phone.tip"/}</p>
						</div>
					</div>
					<div class="form-field-group">
						<div class="form-field input-text">
							<label for="create-view-country">{@i18n key="create-view.country.label"/} :</label>
							<input id="create-view-country" name="country" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="create-view.country.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="create-view-city">{@i18n key="create-view.city.label"/} :</label>
							<input id="create-view-city" name="city" style="width: 20em;" maxlength="20"/>
							<p class="field-tip">{@i18n key="create-view.city.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="create-view-street">{@i18n key="create-view.street.label"/} :</label>
							<input id="create-view-street" name="street" style="width: 20em;" maxlength="20"/>
							<p class="field-tip">{@i18n key="create-view.street.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<input id="create-view-street2" name="street2" style="width: 20em;" maxlength="20"/>
							<p class="field-tip">{@i18n key="create-view.street2.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="create-view-zip">{@i18n key="create-view.zip.label"/} :</label>
							<input id="create-view-zip" name="zip" style="width: 5em;" maxlength="10"/>
							<p class="field-tip">{@i18n key="create-view.zip.tip"/}</p>
						</div>
					</div>
				</div>
				
				
				<div class="common-form tabContent" id="create-view-creditcard-tab">
					<div class="form-field-group">
						<div class="form-field input-text">
							<label for="create-view-type">{@i18n key="create-view.type.label"/} :</label>
							<input id="create-view-type" name="type" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="create-view.type.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="create-view-number">{@i18n key="create-view.number.label"/} :</label>
							<input id="create-view-number" name="number" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="create-view.number.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="create-view-holdername">{@i18n key="create-view.holdername.label"/} :</label>
							<input id="create-view-holdername" name="holdername" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="create-view.holdername.tip"/}</p>
						</div>
					</div>
					<div class="form-field-group">
						<div class="form-field input-text inline">
							<label for="create-view-expireMonth">{@i18n key="create-view.expireMonth.label"/} :</label>
							<input id="create-view-expireMonth" name="expireMonth" style="width: 2em;" maxlength="2"/>
							<p class="field-tip">{@i18n key="create-view.expireMonth.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="create-view-expireYear">{@i18n key="create-view.expireYear.label"/} :</label>
							<input id="create-view-expireYear" name="expireYear" style="width: 4em;" maxlength="4"/>
							<p class="field-tip">{@i18n key="create-view.expireYear.tip"/}</p>
						</div>
					</div>
				</div>
				
			</div>
			<div class="bottom-toolbar">
				<a title="{@i18n key="create-view.execute-create.button.title"/}" class="button executeCreateButton">
					<span class="icon insert"> </span>
					<span class="label">{@i18n key="create-view.execute-create.button"/}</span>
				</a>
				<a title="{@i18n key="close.button.title"/}" href="#close" class="button closeButton">
					<span class="icon close-popup"> </span>
					<span class="label">{@i18n key="close.button"/}</span>
				</a>
			</div>
		</form>
	</div>
</div>

<div id="update-view" class="popupModalDialog">
	<div style="width: 970px;">
		<a href="#close" title="{@i18n key="popupModalDialog.close.title"/}" class="close">X</a>
		<h1>{@i18n key="update-view.title"/} / <span id="update-view-code"></span></h1>
		<ul class="messages"></ul>
		<form action="">
			<div class="body tabContainer">
				<div class="tabs">
					<ul>
						<li data-content="update-view-booking-tab">{@i18n key="update-view.tab-rooms.title"/}</li>
						<li data-content="update-view-requests-tab">{@i18n key="update-view.tab-requests.title"/}</li>
						<li data-content="update-view-contact-tab">{@i18n key="update-view.tab-contact.title"/}</li>
						<li data-content="update-view-creditcard-tab">{@i18n key="update-view.tab-creditcard.title"/}</li>
					</ul>
				</div>
				
				<div class="common-form tabContent" id="update-view-booking-tab">
				
					<div class="form-field-group">
						<div class="form-field input-date">
							<label for="update-view-checkin">{@i18n key="update-view.checkin.label"/} :</label>
							<input id="update-view-checkin" name="checkin"/>
							<p class="field-tip">{@i18n key="update-view.checkin.tip"/}</p>
						</div>
						<div class="form-field input-date">
							<label for="update-view-checkout">{@i18n key="update-view.checkout.label"/} :</label>
							<input id="update-view-checkout" name="checkout"/>
							<p class="field-tip">{@i18n key="update-view.checkout.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="update-view-nights">{@i18n key="update-view.nights.label"/} :</label>
							<input id="update-view-nights" name="nights" style="width: 5em;"/>
							<p class="field-tip">{@i18n key="update-view.nights.tip"/}</p>
						</div>
					</div>
					<div class="form-field-group" style="width: 580px;">
						<table id="update-view-bookings" class="result-list">
							<thead>
								<tr>
									<th scope="col" class="col-icons">
											<a title="{@i18n key="update-view.goto-add-room.button.title"/}" class="button gotoAddRoom">
												<span class="icon goto-add-room"> </span>
											</a>
									</th>
									<th scope="col" class="col-text">{@i18n key="update-view.table.title.type"/}</th>
									<th scope="col" class="col-text">{@i18n key="update-view.table.title.name"/}</th>
									<th scope="col" class="col-number">{@i18n key="update-view.table.title.adults"/} / {@i18n key="update-view.table.title.children"/}</th>
									<th scope="col" class="col-number">{@i18n key="update-view.table.title.price"/}</th>
								</tr>
							</thead>
							<tfoot>
							<tr>
								<td colspan="4"></td>
								<td id="update-view-total-price" class="col-number">0</td>
							</tr>
							</tfoot>
							<tbody></tbody>
						</table>
					</div>
				</div>

				
				<div class="common-form tabContent" id="update-view-requests-tab">
					<div class="form-field-group">
						<div class="form-field long-text">
							<label for="update-view-specialRequest">{@i18n key="update-view.specialRequest.label"/} :</label>
							<p class="field-tip">{@i18n key="update-view.specialRequest.tip"/}</p>
							<textarea id="update-view-specialRequest" name="specialRequest" style="width: 20em; height: 20em;"> </textarea>
						</div>
					</div>
					<div class="form-field-group">
						<div class="form-field long-text">
							<label for="update-view-notes">{@i18n key="update-view.notes.label"/} :</label>
							<p class="field-tip">{@i18n key="update-view.notes.tip"/}</p>
							<textarea id="update-view-notes" name="notes" style="width: 20em; height: 20em;"> </textarea>
						</div>
					</div>
				</div>
				
				<div class="common-form tabContent" id="update-view-contact-tab">
					<div class="form-field-group">
						<div class="form-field input-text">
							<label for="update-view-firstName">{@i18n key="update-view.firstName.label"/} :</label>
							<input id="update-view-firstName" name="firstName" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="update-view.firstName.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="update-view-secondName">{@i18n key="update-view.secondName.label"/} :</label>
							<input id="update-view-secondName" name="secondName" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="update-view.secondName.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="update-view-idNumber">{@i18n key="update-view.idNumber.label"/} :</label>
							<input id="update-view-idNumber" name="idNumber" style="width: 20em;" maxlength="20"/>
							<p class="field-tip">{@i18n key="update-view.idNumber.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="update-view-email">{@i18n key="update-view.email.label"/} :</label>
							<input id="update-view-email" name="email" style="width: 20em;" />
							<p class="field-tip">{@i18n key="update-view.email.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="update-view-phone">{@i18n key="update-view.phone.label"/} :</label>
							<input id="update-view-phone" name="phone" style="width: 20em;" maxlength="15"/>
							<p class="field-tip">{@i18n key="update-view.phone.tip"/}</p>
						</div>
					</div>
					<div class="form-field-group">
						<div class="form-field input-text">
							<label for="update-view-country">{@i18n key="update-view.country.label"/} :</label>
							<input id="update-view-country" name="country" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="update-view.country.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="update-view-city">{@i18n key="update-view.city.label"/} :</label>
							<input id="update-view-city" name="city" style="width: 20em;" maxlength="20"/>
							<p class="field-tip">{@i18n key="update-view.city.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="update-view-street">{@i18n key="update-view.street.label"/} :</label>
							<input id="update-view-street" name="street" style="width: 20em;" maxlength="20"/>
							<p class="field-tip">{@i18n key="update-view.street.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<input id="update-view-street2" name="street2" style="width: 20em;" maxlength="20"/>
							<p class="field-tip">{@i18n key="update-view.street2.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="update-view-zip">{@i18n key="update-view.zip.label"/} :</label>
							<input id="update-view-zip" name="zip" style="width: 5em;" maxlength="10"/>
							<p class="field-tip">{@i18n key="update-view.zip.tip"/}</p>
						</div>
					</div>
				</div>
				
				
				<div class="common-form tabContent" id="update-view-creditcard-tab">
					<div class="form-field-group">
						<div class="form-field input-text">
							<label for="update-view-type">{@i18n key="update-view.type.label"/} :</label>
							<input id="update-view-type" name="type" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="update-view.type.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="update-view-number">{@i18n key="update-view.number.label"/} :</label>
							<input id="update-view-number" name="number" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="update-view.number.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="update-view-holdername">{@i18n key="update-view.holdername.label"/} :</label>
							<input id="update-view-holdername" name="holdername" style="width: 20em;"/>
							<p class="field-tip">{@i18n key="update-view.holdername.tip"/}</p>
						</div>
					</div>
					<div class="form-field-group">
						<div class="form-field input-text inline">
							<label for="update-view-expireMonth">{@i18n key="update-view.expireMonth.label"/} :</label>
							<input id="update-view-expireMonth" name="expireMonth" style="width: 2em;" maxlength="2"/>
							<p class="field-tip">{@i18n key="update-view.expireMonth.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="update-view-expireYear">{@i18n key="update-view.expireYear.label"/} :</label>
							<input id="update-view-expireYear" name="expireYear" style="width: 4em;" maxlength="4"/>
							<p class="field-tip">{@i18n key="update-view.expireYear.tip"/}</p>
						</div>
					</div>
				</div>
			</div>
			<div class="bottom-toolbar">
				<a title="{@i18n key="update-view.execute-update.button.title"/}" class="button executeUpdateButton">
					<span class="icon update"> </span>
					<span class="label">{@i18n key="update-view.execute-update.button"/}</span>
				</a>
				<a title="{@i18n key="close.button.title"/}" href="#close" class="button closeButton">
					<span class="icon close-popup"> </span>
					<span class="label">{@i18n key="close.button"/}</span>
				</a>
			</div>
		</form>
	</div>
</div>

<div id="delete-view" class="popupModalDialog">
	<div style="width: 970px;">
		<a href="#close" title="{@i18n key="popupModalDialog.close.title"/}" class="close">X</a>
		<h1>{@i18n key="delete-view.title"/} / <span id="delete-view-code"></span></h1>
		<ul class="messages"></ul>
		<form action="">
			<div class="body tabContainer">
				<div class="tabs">
					<ul>
						<li data-content="delete-view-booking-tab">{@i18n key="delete-view.tab-rooms.title"/}</li>
						<li data-content="delete-view-requests-tab">{@i18n key="delete-view.tab-requests.title"/}</li>
						<li data-content="delete-view-contact-tab">{@i18n key="delete-view.tab-contact.title"/}</li>
						<li data-content="delete-view-creditcard-tab">{@i18n key="delete-view.tab-creditcard.title"/}</li>
					</ul>
				</div>
			
			
				<div class="common-form tabContent" id="delete-view-booking-tab">
					<div class="form-field-group">
						<div class="form-field input-date">
							<label for="delete-view-checkin">{@i18n key="delete-view.checkin.label"/} :</label>
							<input id="delete-view-checkin" name="checkin" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.checkin.tip"/}</p>
						</div>
						<div class="form-field input-date">
							<label for="delete-view-checkout">{@i18n key="delete-view.checkout.label"/} :</label>
							<input id="delete-view-checkout" name="checkout" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.checkout.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="delete-view-nights">{@i18n key="delete-view.nights.label"/} :</label>
							<input id="delete-view-nights" name="nights" style="width: 5em;" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.nights.tip"/}</p>
						</div>
					</div>
					<div class="form-field-group" style="width: 580px;">
						<table id="delete-view-bookings" class="result-list">
							<thead>
								<tr>
									<th scope="col" class="col-text">{@i18n key="delete-view.table.title.type"/}</th>
									<th scope="col" class="col-text">{@i18n key="delete-view.table.title.name"/}</th>
									<th scope="col" class="col-number">{@i18n key="delete-view.table.title.adults"/} / {@i18n key="delete-view.table.title.children"/}</th>
									<th scope="col" class="col-number">{@i18n key="delete-view.table.title.price"/}</th>
								</tr>
							</thead>
							<tfoot>
							<tr>
								<td colspan="4"></td>
								<td id="delete-view-total-price" class="col-number">0</td>
							</tr>
							</tfoot>
							<tbody></tbody>
						</table>
					</div>
				</div>
			
				<div class="common-form tabContent" id="delete-view-requests-tab">
					<div class="form-field-group">
						<div class="form-field long-text">
							<label for="delete-view-specialRequest">{@i18n key="delete-view.specialRequest.label"/} :</label>
							<p class="field-tip">{@i18n key="delete-view.specialRequest.tip"/}</p>
							<textarea id="delete-view-specialRequest" name="specialRequest" style="width: 20em; height: 20em;" disabled="disabled"> </textarea>
						</div>
					</div>
					<div class="form-field-group">
						<div class="form-field long-text">
							<label for="delete-view-notes">{@i18n key="delete-view.notes.label"/} :</label>
							<p class="field-tip">{@i18n key="delete-view.notes.tip"/}</p>
							<textarea id="delete-view-notes" name="notes" style="width: 20em; height: 20em;" disabled="disabled"> </textarea>
						</div>
					</div>
				</div>
				
				<div class="common-form tabContent" id="delete-view-contact-tab">
					<div class="form-field-group">
						<div class="form-field input-text">
							<label for="delete-view-firstName">{@i18n key="delete-view.firstName.label"/} :</label>
							<input id="delete-view-firstName" name="firstName" style="width: 20em;" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.firstName.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="delete-view-secondName">{@i18n key="delete-view.secondName.label"/} :</label>
							<input id="delete-view-secondName" name="secondName" style="width: 20em;" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.secondName.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="delete-view-idNumber">{@i18n key="delete-view.idNumber.label"/} :</label>
							<input id="delete-view-idNumber" name="idNumber" style="width: 20em;" maxlength="20" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.idNumber.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="delete-view-email">{@i18n key="delete-view.email.label"/} :</label>
							<input id="delete-view-email" name="email" style="width: 20em;"  disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.email.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="delete-view-phone">{@i18n key="delete-view.phone.label"/} :</label>
							<input id="delete-view-phone" name="phone" style="width: 20em;" maxlength="15" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.phone.tip"/}</p>
						</div>
					</div>
					<div class="form-field-group">
						<div class="form-field input-text">
							<label for="delete-view-country">{@i18n key="delete-view.country.label"/} :</label>
							<input id="delete-view-country" name="country" style="width: 20em;" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.country.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="delete-view-city">{@i18n key="delete-view.city.label"/} :</label>
							<input id="delete-view-city" name="city" style="width: 20em;" maxlength="20" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.city.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="delete-view-street">{@i18n key="delete-view.street.label"/} :</label>
							<input id="delete-view-street" name="street" style="width: 20em;" maxlength="20" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.street.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<input id="delete-view-street2" name="street2" style="width: 20em;" maxlength="20" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.street2.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="delete-view-zip">{@i18n key="delete-view.zip.label"/} :</label>
							<input id="delete-view-zip" name="zip" style="width: 5em;" maxlength="10" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.zip.tip"/}</p>
						</div>
					</div>
				</div>
				
				
				<div class="common-form tabContent" id="delete-view-creditcard-tab">
					<div class="form-field-group">
						<div class="form-field input-text">
							<label for="delete-view-type">{@i18n key="delete-view.type.label"/} :</label>
							<input id="delete-view-type" name="type" style="width: 20em;" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.type.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="delete-view-number">{@i18n key="delete-view.number.label"/} :</label>
							<input id="delete-view-number" name="number" style="width: 20em;" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.number.tip"/}</p>
						</div>
						<div class="form-field input-text">
							<label for="delete-view-holdername">{@i18n key="delete-view.holdername.label"/} :</label>
							<input id="delete-view-holdername" name="holdername" style="width: 20em;" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.holdername.tip"/}</p>
						</div>
					</div>
					<div class="form-field-group">
						<div class="form-field input-text inline">
							<label for="delete-view-expireMonth">{@i18n key="delete-view.expireMonth.label"/} :</label>
							<input id="delete-view-expireMonth" name="expireMonth" style="width: 2em;" maxlength="2" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.expireMonth.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="delete-view-expireYear">{@i18n key="delete-view.expireYear.label"/} :</label>
							<input id="delete-view-expireYear" name="expireYear" style="width: 4em;" maxlength="4" disabled="disabled"/>
							<p class="field-tip">{@i18n key="delete-view.expireYear.tip"/}</p>
						</div>
					</div>
				</div>
				
			</div>
			<div class="bottom-toolbar">
				<a title="{@i18n key="delete-view.execute-delete.button.title"/}" class="button executeDeleteButton">
					<span class="icon delete"> </span>
					<span class="label">{@i18n key="delete-view.execute-delete.button"/}</span>
				</a>
				<a title="{@i18n key="close.button.title"/}" href="#close" class="button closeButton">
					<span class="icon close-popup"> </span>
					<span class="label">{@i18n key="close.button"/}</span>
				</a>
			</div>
		</form>
	</div>
</div>

<!-- TODO: Dustjs Template -->
<div id="selectroom-view" class="popupModalDialog">
	<div style="width: 970px;">
		<a href="#close" title="{@i18n key="popupModalDialog.close.title"/}" class="close">X</a>
		<h1>{@i18n key="selectroom-view.title"/}</h1>
		<ul class="messages"></ul>
		<form action="">
			<div class="body">
				<div class="common-form">
					<div class="form-field-group">
					   <div class="form-field input-combo-select">
                     <label for="selectroom-view-typeOfRoomId">{@i18n key="selectroom-view.typeOfRoom.label"/} :</label>
                     <select id="selectroom-view-typeOfRoomId" name="typeOfRoomId"></select>
                     <p class="field-tip">{@i18n key="selectroom-view.typeOfRoom.tip"/}</p>
                  </div>
                  <div class="form-field boolean-checkbox">
                     <input id="selectroom-view-onlyAvailable" name="onlyAvailable" type="checkbox" checked="checked" >
                     <label for="selectroom-view-onlyAvailable">{@i18n key="selectroom-view.onlyAvailable.label"/} :</label>
                     <p class="field-tip">{@i18n key="selectroom-view.onlyAvailable.tip"/}</p>
                  </div>
                  <hr/>
						<div class="form-field input-text inline">
							<label for="selectroom-view-adults">{@i18n key="selectroom-view.adults.label"/} :</label>
							<input id="selectroom-view-adults" name="adults" style="width: 5em;"/>
							<p class="field-tip">{@i18n key="selectroom-view.adults.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="selectroom-view-children">{@i18n key="selectroom-view.children.label"/} :</label>
							<input id="selectroom-view-children" name="children" style="width: 5em;"/>
							<p class="field-tip">{@i18n key="selectroom-view.children.tip"/}</p>
						</div>
						<div class="form-field input-text inline">
							<label for="selectroom-view-price">{@i18n key="selectroom-view.price.label"/} :</label>
							<input id="selectroom-view-price" name="price" style="width: 5em;"/>
							<p class="field-tip">{@i18n key="selectroom-view.price.tip"/}</p>
						</div>
					</div>
					<div class="form-field-group" style="width: 530px;">
						<table class="result-list" id="selectroom-view-rooms">
							<thead>
								<tr>
									<th scope="col" class="col-icons"></th>
									<th scope="col" class="col-text">{@i18n key="selectroom-view.table.title.type"/}</th>
									<th scope="col" class="col-text">{@i18n key="selectroom-view.table.title.name"/}</th>
									<th scope="col" class="col-number">{@i18n key="selectroom-view.table.title.accommodates"/}</th>
									<th scope="col" class="col-number">{@i18n key="selectroom-view.table.title.price"/}</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="bottom-toolbar">
				<a title="{@i18n key="selectroom-view.execute-add.button.title"/}" class="button executeAddRoomButton">
					<span class="icon add-room"> </span>
					<span class="label">{@i18n key="selectroom-view.execute-add-room.button"/}</span>
				</a>
				<a title="{@i18n key="close.button.title"/}" href="#close" class="button closeButton">
					<span class="icon close-popup"> </span>
					<span class="label">{@i18n key="close.button"/}</span>
				</a>
			</div>
		</form>
	</div>
</div>
{/pageContent}

{<footerJSContent}
   <script type="text/javascript" src="{@confParam key="contextpath"/}/core/library/jquery/jquery.mockjax.js"></script>
	<script type="text/javascript" src="{@confParam key="contextpath"/}/booking/booking/mocks.js"></script>
   <script type="text/javascript" src="{@confParam key="contextpath"/}/booking/booking/booking.js"></script>
{/footerJSContent}
